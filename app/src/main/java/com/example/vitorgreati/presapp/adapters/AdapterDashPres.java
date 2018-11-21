package com.example.vitorgreati.presapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.Presentation;

import java.util.List;

public class AdapterDashPres extends RecyclerView.Adapter<AdapterDashPres.ViewHolder> {

    private List<Presentation> presentations;

    private static OnItemClickListener itemClickListener;

    public AdapterDashPres(List<Presentation> presentations, OnItemClickListener itemClickListener) {
        this.presentations = presentations;
        this.itemClickListener = itemClickListener;
    }

    public List<Presentation> getPresentations() {
        return this.presentations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dash_presents_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Presentation p = presentations.get(i);
        viewHolder.tvPresTitle.setText(p.getTitle());
        viewHolder.tvPresDesc.setText(p.getDescription());
    }

    @Override
    public int getItemCount() {
        return presentations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPresTitle;
        private TextView tvPresDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPresTitle = itemView.findViewById(R.id.tvPresTitle);
            tvPresDesc = itemView.findViewById(R.id.tvPresDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null)
                        itemClickListener.onItemClick(getAdapterPosition(), v);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos, View v);
    }

}
