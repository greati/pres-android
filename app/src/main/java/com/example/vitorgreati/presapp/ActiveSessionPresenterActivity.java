package com.example.vitorgreati.presapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.vitorgreati.presapp.adapters.AdapterQuestionSessionActive;
import com.example.vitorgreati.presapp.model.Question;

import java.util.ArrayList;
import java.util.List;

public class ActiveSessionPresenterActivity extends AppCompatActivity {

    private RecyclerView rvQuestions;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_session_presenter);

        rvQuestions = findViewById(R.id.rvQuestions);

        layoutManager = new LinearLayoutManager(this);
        rvQuestions.setLayoutManager(layoutManager);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question("Question 1", null));
        questions.add(new Question("Question 2", null));

        adapter = new AdapterQuestionSessionActive(questions);

        rvQuestions.setAdapter(adapter);

    }
}
