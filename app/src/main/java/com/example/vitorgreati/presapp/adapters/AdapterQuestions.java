package com.example.vitorgreati.presapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;

import java.util.List;

public class AdapterQuestions extends RecyclerView.Adapter<AdapterQuestions.ViewHolder> {

    private List<ChoicesQuestion> questions;

    private static OnItemClickListener itemClickListener;

    public AdapterQuestions(List<ChoicesQuestion> questions, OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
        this.questions = questions;
    }

    public List<ChoicesQuestion> getSessions() {
        return this.questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChoicesQuestion s = questions.get(i);
        viewHolder.tvTitle.setText(s.getTitle());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);

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
