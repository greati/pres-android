package com.example.vitorgreati.presapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.PresSession;

import java.util.List;

public class AdapterSession extends RecyclerView.Adapter<AdapterSession.ViewHolder> {

    private List<PresSession> sessions;

    private final OnItemClickListener itemClickListener;

    public AdapterSession(List<PresSession> sessions, OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.sessions = sessions;
    }

    public List<PresSession> getSessions() {
        return this.sessions;
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
        PresSession s = sessions.get(i);
        viewHolder.tvPresTitle.setText(s.getPresentation().getTitle());
        viewHolder.tvLocal.setText(s.getLocation().getDescription());
        viewHolder.tvDateTime.setText(s.getDateTime().toString());
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvPresTitle;
        public TextView tvLocal;
        public TextView tvDateTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPresTitle = itemView.findViewById(R.id.tvPresTitle);
            tvLocal = itemView.findViewById(R.id.tvLocal);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);

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
