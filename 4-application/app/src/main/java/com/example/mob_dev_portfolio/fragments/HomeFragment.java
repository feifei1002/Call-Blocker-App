package com.example.mob_dev_portfolio.fragments;

import static android.provider.CallLog.Calls.LIMIT_PARAM_KEY;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CallLog;
import android.provider.ContactsContract;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mob_dev_portfolio.ContactData;
import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.adapters.ContactDataAdapter;
import com.example.mob_dev_portfolio.adapters.ReportListAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList<ContactData> contactDataList = new ArrayList<ContactData>();
    RecyclerView contactDataView;
    private ContactDataAdapter contactDataAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String contactName;
    private String phoneNumber;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        contactDataView = view.findViewById(R.id.contact_list_view);
        getPhoneContacts();
        for(int i = 0; i < contactDataList.size(); i++){
            contactName = contactDataList.get(i).getContactName();
            phoneNumber = contactDataList.get(i).getPhoneNumber();
        }
        contactDataAdapter = new ContactDataAdapter(contactDataList);
        layoutManager = new LinearLayoutManager(getContext());
        contactDataView.setLayoutManager(layoutManager);
        contactDataView.setAdapter(contactDataAdapter);
        return view;
    }


    //Please test this on a phone that has contact data
    private void getPhoneContacts() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[] {Manifest.permission.READ_CALL_LOG}, 0);
        }
        ContentResolver contentResolver = getActivity().getContentResolver();
        //Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Uri uri = CallLog.Calls.CONTENT_URI;
        //Cursor cursor = contentResolver.query(uri.buildUpon().appendQueryParameter(LIMIT_PARAM_KEY, "10")
                //.build(), null, null, null, CallLog.Calls.DATE + " DESC");
        Cursor cursor = contentResolver.query(uri, null, null, null, CallLog.Calls.DATE + " DESC");
        Log.i("CONTACT_PROVIDER", "TOTAL # OF CONTACTS ::: " + Integer.toString(cursor.getCount()));
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ContactData contactData = new ContactData();
                String contactName = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.CACHED_NAME));
                contactData.setContactName(contactName);
                String contactNumber = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.NUMBER));
                contactData.setPhoneNumber(contactNumber);
                String callDate = cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE));
                //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date dateFormat= new Date(Long.valueOf(callDate));
                String callDateFormatted = String.valueOf(dateFormat);
                contactData.setCallDate(callDateFormatted);
                contactDataList.add(contactData);
                Log.i("CONTACT_PROVIDER", "Contact Name: " + contactName + "  Phone No: " + contactNumber + "  Call Date: " + callDateFormatted);
            }
        }
    }
}