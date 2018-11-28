package com.example.vitorgreati.presapp.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitorgreati.presapp.NewChoiceQuestionActivity;
import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.adapters.AdapterQuestions;
import com.example.vitorgreati.presapp.dao.impl.ChoicesQuestionWebDAO;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Alternative;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;

import java.util.ArrayList;
import java.util.List;

public class SessionQuestionsFragment extends Fragment implements AdapterQuestions.OnItemClickListener {

    public static final int REQ_NEW_CHOICE_QUESTION = 1;
    public static final int RES_NEW_CHOICE_QUESTION = 1;

    private FloatingActionButton fabAddQuestion;

    private RecyclerView recyclerQuestions;
    private RecyclerView.Adapter adapterQuestions;
    private RecyclerView.LayoutManager layoutManagerSession;

    private List<ChoicesQuestion> questions;

    private PresSession session;

    public static Fragment newInstance(PresSession s) {
        Fragment instance = new SessionQuestionsFragment();
        Bundle args = new Bundle();
        args.putSerializable("session", s);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = (PresSession) getArguments().getSerializable("session");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.questions_fragment, container, false);

        recyclerQuestions = v.findViewById(R.id.recyclerQuestions);
        recyclerQuestions.setHasFixedSize(true);

        layoutManagerSession = new LinearLayoutManager(container.getContext());
        recyclerQuestions.setLayoutManager(layoutManagerSession);

        questions = new ArrayList<>();

        adapterQuestions = new AdapterQuestions(questions, this);
        recyclerQuestions.setAdapter(adapterQuestions);

        fabAddQuestion = v.findViewById(R.id.fabAddQuestion);
        fabAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), NewChoiceQuestionActivity.class);
                i.putExtra("session", session);
                startActivityForResult(i, REQ_NEW_CHOICE_QUESTION);
            }
        });

        new ListQuestionsAsyncTask().execute(session);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case RES_NEW_CHOICE_QUESTION:
                ChoicesQuestion q = (ChoicesQuestion) data.getSerializableExtra("question");
                questions.add(q);
                adapterQuestions.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onItemClick(int p, View v) {

    }

    private class ListQuestionsAsyncTask extends AsyncTask<PresSession, Void, List<ChoicesQuestion>> {

        private Exception e;

        @Override
        protected List<ChoicesQuestion> doInBackground(PresSession... sessions) {
            try {
                return ChoicesQuestionWebDAO.getInstance().list(sessions[0]);
            } catch (WebException e1) {
                e = e1;
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<ChoicesQuestion> choicesQuestions) {
            super.onPostExecute(choicesQuestions);
            if (e != null) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                questions.clear();
                questions.addAll(choicesQuestions);
                adapterQuestions.notifyDataSetChanged();
            }
        }
    }
}
