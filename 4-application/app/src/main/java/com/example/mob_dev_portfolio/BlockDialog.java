package com.example.mob_dev_portfolio;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.mob_dev_portfolio.database.BlockList;
import com.example.mob_dev_portfolio.database.BlockListDatabase;
import com.example.mob_dev_portfolio.fragments.BlockFragment;
import com.example.mob_dev_portfolio.fragments.SearchFragment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockDialog extends DialogFragment {
    private static final String TAG = "BlockDialog";

    private TextView yesBtn;
    private TextView cancelBtn;
    private TextView askBlockPhoneNo;
    private String phoneNoBlock;
    private ExecutorService executorService;

    public static BlockDialog newInstance() {
        Bundle args = new Bundle();
        BlockDialog fragment = new BlockDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            phoneNoBlock = getArguments().getString("phoneNoBlock");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.block_dialog, container, false);
        askBlockPhoneNo = view.findViewById(R.id.askBlockPhoneNo_textView);
        askBlockPhoneNo.setText(phoneNoBlock);
        yesBtn = view.findViewById(R.id.action_yes);
        cancelBtn = view.findViewById(R.id.action_cancel);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });
        this.executorService = Executors.newFixedThreadPool(4);
        BlockListDatabase listDatabase = Room.databaseBuilder(getContext(), BlockListDatabase.class, "Block List Database").build();
        yesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");
                BlockList blockListSubmit = new BlockList(phoneNoBlock);
                //BlockList blockListTest = new BlockList("0123456789");
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        listDatabase.blockListDAO().getAllBlockList();
                        listDatabase.blockListDAO().insertAll(blockListSubmit);
                        listDatabase.blockListDAO().getAllBlockList();
                    }
                });
                getDialog().dismiss();

                BlockFragment blockFragment = new BlockFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, blockFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
