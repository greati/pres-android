package com.example.vitorgreati.presapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.adapters.AdapterChoiceCheck;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;

public class ChoiceQuestionFragment extends Fragment {

    private TextView tvTitle;

    private ChoicesQuestion question;

    private ListView lvAlternatives;

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

        return v;
    }
}
