package com.example.mob_dev_portfolio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.mob_dev_portfolio.database.AppDatabase;
import com.example.mob_dev_portfolio.database.ReportForm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportListFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    ExecutorService executorService;
    private String mParam1;
    private String mParam2;
    ArrayList<ReportForm> reportList = new ArrayList<ReportForm>();
    RecyclerView reportListView;
    private ReportListAdapter reportListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public ReportListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportListFragment newInstance(String param1, String param2) {
        ReportListFragment fragment = new ReportListFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report_list, container, false);
        reportListView = view.findViewById(R.id.report_list_view);
        AppDatabase appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "App Database").allowMainThreadQueries().build();
        reportList = (ArrayList<ReportForm>) appDatabase.reportFormDAO().getAllReportForms();
//        this.executorService = Executors.newFixedThreadPool(4);
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                reportList = (ArrayList<ReportForm>) appDatabase.reportFormDAO().getAllReportForms();
//            }
//       });
        reportListAdapter = new ReportListAdapter(reportList);
        layoutManager = new LinearLayoutManager(getContext());
        reportListView.setLayoutManager(layoutManager);
        reportListView.setAdapter(reportListAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        reportListAdapter = new ReportListAdapter(reportList);
//        layoutManager = new LinearLayoutManager(getContext());
//        reportListView.setLayoutManager(layoutManager);
//        reportListView.setAdapter(reportListAdapter);
        reportListAdapter.notifyDataSetChanged();
    }
}