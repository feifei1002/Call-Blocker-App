package com.example.mob_dev_portfolio.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob_dev_portfolio.ContactData;
import com.example.mob_dev_portfolio.R;

import java.util.ArrayList;

public class ContactDataAdapter extends RecyclerView.Adapter<ContactDataAdapter.ViewHolder> {
    private ArrayList<ContactData> contactDataList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewNumber;
        public TextView textViewDate;
        public ViewHolder(@NonNull View textView) {
            super(textView);
            textViewName = textView.findViewById(R.id.name_textView);
            textViewNumber = textView.findViewById(R.id.number_textView);
            textViewDate = textView.findViewById(R.id.date_textView);
        }
    }

    public ContactDataAdapter(ArrayList<ContactData> contactDataList) {
        this.contactDataList = contactDataList;
        notifyDataSetChanged();
    }

    @Override
    public ContactDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calllog, parent, false);
        ContactDataAdapter.ViewHolder vh = new ContactDataAdapter.ViewHolder(new TextView(parent.getContext()));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactDataAdapter.ViewHolder holder, final int position) {
        ContactData contactData = contactDataList.get(position);
        holder.textViewName.setText(contactData.getContactName());
        holder.textViewNumber.setText(contactData.getPhoneNumber());
        holder.textViewDate.setText(contactData.getCallDate());
    }

    @Override
    public int getItemCount() {
        return contactDataList.size();
    }
}
