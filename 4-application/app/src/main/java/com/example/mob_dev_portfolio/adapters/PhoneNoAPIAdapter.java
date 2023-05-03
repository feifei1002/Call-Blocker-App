package com.example.mob_dev_portfolio.adapters;

import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob_dev_portfolio.classes.PhoneNoAPI;

import java.util.ArrayList;

public class PhoneNoAPIAdapter extends RecyclerView.Adapter<PhoneNoAPIAdapter.ViewHolder> {
    private ArrayList<PhoneNoAPI> phoneNoAPIS;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    public PhoneNoAPIAdapter(ArrayList<PhoneNoAPI> phoneNoAPIS) {
        this.phoneNoAPIS = phoneNoAPIS;
    }

    @Override
    public PhoneNoAPIAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(new TextView(parent.getContext()));
        vh.textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.textView.setText(phoneNoAPIS.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return phoneNoAPIS.size();
    }
}
