package com.example.vitorgreati.presapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.Participation;
import com.example.vitorgreati.presapp.model.PresSession;

import java.util.List;

public class AdapterParticipation extends RecyclerView.Adapter<AdapterParticipation.ViewHolder> {

    private List<Participation> parts;

    private final OnItemClickListener itemClickListener;
    private final Context ctx;

    public AdapterParticipation(List<Participation> sessions, Context ctx, OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.ctx = ctx;
        this.parts = sessions;
    }

    public List<Participation> getParts() {
        return this.parts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dash_attends_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Participation s = parts.get(i);
        viewHolder.tvPresTitle.setText(s.getSession().getPresentation().getTitle());
        viewHolder.tvLocal.setText(s.getSession().getLocation().getDescription());
        viewHolder.tvDateTime.setText(s.getSession().getDateTime().toString());
        viewHolder.tvAuthorName.setText(s.getSession().getPresentation().getUser().getName());

        if (s.getActive()) {
            viewHolder.tvActive.setBackgroundColor(ctx.getResources().getColor(android.R.color.holo_green_dark));
        } else {
            viewHolder.tvActive.setBackgroundColor(ctx.getResources().getColor(android.R.color.darker_gray));
        }

    }

    @Override
    public int getItemCount() {
        return parts.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPresTitle;
        public TextView tvLocal;
        public TextView tvDateTime;
        public TextView tvAuthorName;
        public TextView tvActive;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPresTitle = itemView.findViewById(R.id.tvPresTitle);
            tvLocal = itemView.findViewById(R.id.tvLocal);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            tvAuthorName = itemView.findViewById(R.id.tvAuthorName);
            tvActive = itemView.findViewById(R.id.tvActive);

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
        void onItemClick(int p, View v);
    }
}
