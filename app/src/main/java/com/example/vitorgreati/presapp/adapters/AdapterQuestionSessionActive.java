package com.example.vitorgreati.presapp.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.config.AppUtils;
import com.example.vitorgreati.presapp.dao.impl.ChoicesQuestionWebDAO;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;

import java.util.List;

public class AdapterQuestionSessionActive extends RecyclerView.Adapter<AdapterQuestionSessionActive.ViewHolder> {

    private List<ChoicesQuestion> questions;

    private Context ctx;

    public AdapterQuestionSessionActive(List<ChoicesQuestion> questions, Context ctx) {
        this.questions = questions;
        this.ctx = ctx;
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
        ChoicesQuestion q = questions.get(i);
        viewHolder.tvQuestionTitle.setText(q.getTitle());
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvQuestionTitle;
        private ImageButton btOpenQuestion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvQuestionTitle = itemView.findViewById(R.id.tvQuestionTitle);
            this.btOpenQuestion = itemView.findViewById(R.id.btActivateQuestion);

            btOpenQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int p = getAdapterPosition();
                    ChoicesQuestion question = questions.get(p);
                    new OpenQuestionAsyncTask().execute(question);
                }
            });
        }
    }

    private class OpenQuestionAsyncTask extends AsyncTask<ChoicesQuestion, Void, Void> {

        private Exception e;

        @Override
        protected Void doInBackground(ChoicesQuestion... choicesQuestions) {

            try {
                ChoicesQuestionWebDAO.getInstance().open(AppUtils.getLoggedUser(ctx), choicesQuestions[0]);
            } catch (WebException e1) {
                this.e = e1;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (e != null) {
                Toast.makeText(ctx, "Error when opening" + e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(ctx, "Opened", Toast.LENGTH_LONG).show();
            }
        }
    }

}
