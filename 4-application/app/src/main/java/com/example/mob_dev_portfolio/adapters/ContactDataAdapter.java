package com.example.mob_dev_portfolio.adapters;

import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob_dev_portfolio.ContactData;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ContactDataAdapter extends RecyclerView.Adapter<ContactDataAdapter.ViewHolder> {
    private String contactName;
    private String phoneNumber;
    private ArrayList<ContactData> contactDataList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public TextView textView2;
        public ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    public ContactDataAdapter(ArrayList<ContactData> contactDataList) {
        this.contactDataList = contactDataList;
    }

    @Override
    public ContactDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ContactDataAdapter.ViewHolder vh = new ContactDataAdapter.ViewHolder(new TextView(parent.getContext()));
        vh.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactDataAdapter.ViewHolder holder, final int position) {
        holder.textView.setText(contactDataList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return contactDataList.size();
    }
}
