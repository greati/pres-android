package com.example.vitorgreati.presapp.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.adapters.AdapterChoiceCheck;
import com.example.vitorgreati.presapp.config.AppUtils;
import com.example.vitorgreati.presapp.dao.impl.ChoicesQuestionWebDAO;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Alternative;
import com.example.vitorgreati.presapp.model.ChoicesAnswer;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChoiceQuestionFragment extends Fragment {

    private TextView tvTitle;

    private ChoicesQuestion question;

    private ListView lvAlternatives;

    private Button btAnswer;

    public static Fragment newInstance(ChoicesQuestion question) {
        Fragment instance = new ChoiceQuestionFragment();
        Bundle args = new Bundle();
        args.putSerializable("question", question);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        question = (ChoicesQuestion) getArguments().getSerializable("question");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.choice_question_fragment, container, false);

        tvTitle = v.findViewById(R.id.tvTitle);

        tvTitle.setText(question.getTitle());

        lvAlternatives = v.findViewById(R.id.lvAlternatives);
        lvAlternatives.setAdapter(new AdapterChoiceCheck(getContext(), 0, question.getAlternatives()));

        this.btAnswer = v.findViewById(R.id.btAnswer);
        btAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Alternative> choices = new ArrayList<>();

                for (Alternative a : question.getAlternatives())
                    if (a.getChecked())
                        choices.add(a);

                ChoicesAnswer answer = new ChoicesAnswer(question, new Date(), choices);

                new AnswerAsyncTask().execute(answer);

            }
        });

        return v;
    }

    private class AnswerAsyncTask extends AsyncTask<ChoicesAnswer, Void, ChoicesAnswer> {

        private Exception e;

        @Override
        protected ChoicesAnswer doInBackground(ChoicesAnswer... choicesQuestions) {

            try {
                return ChoicesQuestionWebDAO.getInstance().answer(AppUtils.getLoggedUser(getContext()), choicesQuestions[0]);
            } catch (WebException e1) {
                this.e = e1;
            }

            return null;
        }

        @Override
        protected void onPostExecute(ChoicesAnswer answer) {
            super.onPostExecute(answer);

            if (e != null) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Successfully answered", Toast.LENGTH_LONG).show();
                btAnswer.setEnabled(false);
            }
        }
    }
}
