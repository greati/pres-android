package com.example.vitorgreati.presapp.fragments;

import android.content.Intent;
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

import com.example.vitorgreati.presapp.NewChoiceQuestionActivity;
import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.adapters.AdapterQuestions;
import com.example.vitorgreati.presapp.model.Alternative;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class SessionQuestionsFragment extends Fragment implements AdapterQuestions.OnItemClickListener {

    private FloatingActionButton fabAddQuestion;

    private RecyclerView recyclerQuestions;
    private RecyclerView.Adapter adapterQuestions;
    private RecyclerView.LayoutManager layoutManagerSession;

    public static Fragment newInstance(PresSession s) {
        Fragment instance = new SessionQuestionsFragment();
        Bundle args = new Bundle();
        args.putSerializable("session", s);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.questions_fragment, container, false);

        recyclerQuestions = v.findViewById(R.id.recyclerQuestions);
        recyclerQuestions.setHasFixedSize(true);

        layoutManagerSession = new LinearLayoutManager(container.getContext());
        recyclerQuestions.setLayoutManager(layoutManagerSession);

        List<Question> questions = new ArrayList<>();

        Question q1 = new Question("aaaa", new ArrayList<Alternative>());
        Question q2 = new Question("bbbb", new ArrayList<Alternative>());

        questions.add(q1);
        questions.add(q2);

        adapterQuestions = new AdapterQuestions(questions, this);
        recyclerQuestions.setAdapter(adapterQuestions);

        fabAddQuestion = v.findViewById(R.id.fabAddQuestion);
        fabAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), NewChoiceQuestionActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onItemClick(int p, View v) {

    }
}
