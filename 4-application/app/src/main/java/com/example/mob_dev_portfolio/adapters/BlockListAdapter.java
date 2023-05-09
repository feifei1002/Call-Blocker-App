package com.example.mob_dev_portfolio.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mob_dev_portfolio.R;
import com.example.mob_dev_portfolio.database.BlockList;

import java.util.ArrayList;

public class BlockListAdapter extends RecyclerView.Adapter<BlockListAdapter.ViewHolder> {

    private ArrayList<String> blockList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder (View view) {
            super(view);
            textView = view.findViewById(R.id.phoneNo_textView);
        }
    }

    public BlockListAdapter(ArrayList<String> blockList) {
        this.blockList = blockList;
    }

    @Override
    public BlockListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone_no, parent, false);
        BlockListAdapter.ViewHolder vh = new BlockListAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(blockList.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent( v.getContext(), ReportFormDetailsActivity.class);
//                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return blockList.size();
    }
}
