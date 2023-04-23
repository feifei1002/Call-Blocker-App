package com.example.mob_dev_portfolio;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String phoneNo;
    private Button reportBtn;
    private SearchView searchView;
    private RecyclerView phoneNoAPIList;
    private PhoneNoAPIAdapter phoneNoAPIAdapter;
    private ArrayList<PhoneNoAPI> phoneNoAPIS = new ArrayList<PhoneNoAPI>();
    private RecyclerView.LayoutManager layoutManager;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = view.findViewById(R.id.searchView);
        searchView.setQueryHint("Search for a phone number here");
        phoneNoAPIList = view.findViewById(R.id.search_recyclerView);
        reportBtn = view.findViewById(R.id.report_button);
        reportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportFragment reportFragment = new ReportFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, reportFragment);
                fragmentTransaction.commit();
            }
        });
        phoneNoAPIAdapter = new PhoneNoAPIAdapter(phoneNoAPIS);
        layoutManager = new LinearLayoutManager(getContext());
        phoneNoAPIList.setLayoutManager(layoutManager);
        phoneNoAPIList.setAdapter(phoneNoAPIAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                phoneNo = query;
                onRequestPhoneNos(view);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
                //filterList(newText);
            }
        });
        //onRequestPhoneNos(view);

        return view;
    }

    public void onRequestPhoneNos (View view) {
        //searchPhoneNumber();
        String url1 = "https://api.veriphone.io/v2/verify?key=55944A19DF4542349F85B5ED29DCA8AE";
        String url2 = "https://api.veriphone.io/v2/verify?phone=%2B49-15123577723&key=55944A19DF4542349F85B5ED29DCA8AE";
        String url3 = "https://api.veriphone.io/v2/verify?phone="+phoneNo+"&key=55944A19DF4542349F85B5ED29DCA8AE";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url3, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("Response", response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error.toString());
                        Toast.makeText(getContext(), error.toString(),Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    private void populateList(JSONArray items){
        phoneNoAPIS.clear();
        try{
            for (int i =0; i<items.length();i++) {
                JSONObject jo = items.getJSONObject(i);
                phoneNoAPIS.add(new PhoneNoAPI(jo.getString("number")));
            }
        }
        catch(JSONException err){}
        phoneNoAPIAdapter.notifyDataSetChanged();
    }

}