package com.example.mob_dev_portfolio.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.adapters.BlockListAdapter;
import com.example.mob_dev_portfolio.databases.BlockList;
import com.example.mob_dev_portfolio.databases.BlockListDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlockFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlockFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<BlockList> blockLists = new ArrayList<BlockList>();
    RecyclerView blockListView;
    private BlockListAdapter blockListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ExecutorService executorService;

    public BlockFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlockFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlockFragment newInstance(String param1, String param2) {
        BlockFragment fragment = new BlockFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_block, container, false);
        blockListView = view.findViewById(R.id.block_list_view);
        ArrayList<String> phoneNumberBlockList = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(4);
        BlockListDatabase listDatabase = Room.databaseBuilder(getContext(), BlockListDatabase.class, "Block List Database").allowMainThreadQueries().build();
//        listDatabase.blockListDAO().deleteAll();
        List<BlockList> blockLists = listDatabase.blockListDAO().getAllBlockList();
        for(int i = 0; i < blockLists.size(); i++) {
            String blockNumber = blockLists.get(i).getPhoneNo();
            phoneNumberBlockList.add(blockNumber);
            Log.d("BLOCK_LIST_LOGTAG", blockLists.toString());
        }

        blockListAdapter = new BlockListAdapter(phoneNumberBlockList);
        layoutManager = new LinearLayoutManager(getContext());
        blockListView.setLayoutManager(layoutManager);
        blockListView.setAdapter(blockListAdapter);

        return view;
    }
}