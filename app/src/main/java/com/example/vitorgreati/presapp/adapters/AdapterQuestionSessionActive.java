package com.example.vitorgreati.presapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.Question;

import java.util.List;

public class AdapterQuestionSessionActive extends RecyclerView.Adapter<AdapterQuestionSessionActive.ViewHolder> {

    private List<Question> questions;

    public AdapterQuestionSessionActive(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.question_session_active_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Question q = questions.get(i);
        viewHolder.tvQuestionTitle.setText(q.getTitle());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvQuestionTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvQuestionTitle = itemView.findViewById(R.id.tvQuestionTitle);
        }
    }

}
