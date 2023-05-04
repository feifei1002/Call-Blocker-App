package com.example.mob_dev_portfolio.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob_dev_portfolio.classes.ContactData;
import com.example.mob_dev_portfolio.R;

import java.util.ArrayList;

public class ContactDataAdapter extends RecyclerView.Adapter<ContactDataAdapter.ViewHolder> {
    private ArrayList<ContactData> contactDataList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewNumber;
        public TextView textViewDate;
        public ImageView imageViewCallType;
        public ViewHolder(@NonNull View view) {
            super(view);
            textViewName = view.findViewById(R.id.name_textView);
            textViewNumber = view.findViewById(R.id.number_textView);
            textViewDate = view.findViewById(R.id.date_textView);
            imageViewCallType = view.findViewById(R.id.callType_imageView);

        }
    }

    public ContactDataAdapter(ArrayList<ContactData> contactDataList) {
        this.contactDataList = contactDataList;
        notifyDataSetChanged();
    }

    @Override
    public ContactDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calllog, parent, false);
        //ContactDataAdapter.ViewHolder vh = new ContactDataAdapter.ViewHolder(new TextView(parent.getContext()));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        ContactData contactData = contactDataList.get(position);
        if(contactData.getContactName() == null || contactData.getContactName().isEmpty()) {
            holder.textViewName.setText(R.string.unknown);
        }else {
            holder.textViewName.setText(contactData.getContactName());
        }
        holder.textViewNumber.setText(contactData.getPhoneNumber());
        holder.textViewDate.setText(contactData.getCallDate());
        if (contactData.getCallType().equals("INCOMING")) {
            holder.imageViewCallType.setImageResource(R.drawable.ic_baseline_call_received_24);
        }
        else if(contactData.getCallType().equals("OUTGOING")) {
            holder.imageViewCallType.setImageResource(R.drawable.ic_baseline_call_made_24);
        }
        else if(contactData.getCallType().equals("MISSED")) {
            holder.imageViewCallType.setImageResource(R.drawable.ic_baseline_call_missed_24);
        }

    }

    @Override
    public int getItemCount() {
        return contactDataList.size();
    }
}
