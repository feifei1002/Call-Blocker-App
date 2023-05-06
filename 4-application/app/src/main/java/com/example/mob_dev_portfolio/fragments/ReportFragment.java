package com.example.mob_dev_portfolio.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.database.AppDatabase;
import com.example.mob_dev_portfolio.database.ReportForm;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    ExecutorService executorService;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button submitBtn;
    String phoneNo;

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
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
            phoneNo = getArguments().getString("phoneNo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        Spinner spinner = view.findViewById(R.id.spinner_callType);
        TextInputEditText phoneNumber = view.findViewById(R.id.textInputPhoneNumber);
        TextInputEditText ownerName = view.findViewById(R.id.textInputOwnerName);
        TextInputEditText yourName = view.findViewById(R.id.textInputYourName);
        TextInputEditText comment = view.findViewById(R.id.textInputComment);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.callType_spinner, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);

        AppDatabase appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "App Database").build();
        this.executorService = Executors.newFixedThreadPool(4);
            phoneNumber.setText(phoneNo);

        submitBtn = view.findViewById(R.id.buttonSubmit);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumberInput = phoneNumber.getText().toString();
                String ownerNameInput = ownerName.getText().toString();
                String yourNameInput = yourName.getText().toString();
                String callTypeInput = spinner.getSelectedItem().toString();
                String commentInput = comment.getText().toString();
                ReportForm reportFormSubmit = new ReportForm(phoneNumberInput, ownerNameInput, yourNameInput, callTypeInput, commentInput);
                //ReportForm reportForm1 = new ReportForm("0123456788", "Cheng", "Fei", "Scam", "It's a scam");
                //ReportForm reportForm2 = new ReportForm("035465688", "George", "Fei", "Prank call", "It's a prank call");
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        appDatabase.reportFormDAO().getAllReportForms();
                        appDatabase.reportFormDAO().insertAll(reportFormSubmit);
                        List<ReportForm> reportForms = appDatabase.reportFormDAO().getAllReportForms();
                        for(ReportForm reportForm : reportForms){
                            Log.i("ReportForm", reportForm.toString());
                        }
                        ReportListFragment reportListFragment = new ReportListFragment();
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.frame_layout, reportListFragment);
                        fragmentManager.popBackStackImmediate();
                        fragmentTransaction.commit();
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}