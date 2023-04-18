package com.example.mob_dev_portfolio;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob_dev_portfolio.database.ReportForm;

import java.util.ArrayList;

public class ReportListAdapter extends RecyclerView.Adapter<ReportListAdapter.ViewHolder> {
    private ArrayList<ReportForm> reportForms;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(TextView view) {
            super(view);
            textView = view;
        }
    }

    public ReportListAdapter(ArrayList<ReportForm> reportForms){
        this.reportForms = reportForms;
    }

    @Override
    public ReportListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder vh = new ViewHolder(new TextView(parent.getContext()));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(reportForms.get(position).toString());

    }

    @Override
    public int getItemCount() {
        return reportForms.size();
    }

}
