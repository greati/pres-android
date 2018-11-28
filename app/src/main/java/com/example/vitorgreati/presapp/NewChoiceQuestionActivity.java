package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vitorgreati.presapp.adapters.AdapterChoice;
import com.example.vitorgreati.presapp.dao.impl.ChoicesQuestionWebDAO;
import com.example.vitorgreati.presapp.dao.interfaces.ChoicesQuestionDAO;
import com.example.vitorgreati.presapp.dialogs.NewChoiceDialog;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.fragments.SessionQuestionsFragment;
import com.example.vitorgreati.presapp.model.Alternative;
import com.example.vitorgreati.presapp.model.ChoicesAnswer;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.PresSession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NewChoiceQuestionActivity extends AppCompatActivity implements NewChoiceDialog.OnPositiveListener {

    private Button btNewChoice, btnConfirm, btnCancel;
    private ListView lvChoices;
    private EditText edtTitle;

    private AdapterChoice adapterChoice;
    private List<Alternative> alternatives;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_choice_question);

        lvChoices = findViewById(R.id.lvChoices);

        alternatives = new ArrayList<>();

        adapterChoice = new AdapterChoice(this, alternatives);

        adapterChoice.sort(new Comparator<Alternative>() {
            @Override
            public int compare(Alternative o1, Alternative o2) {
                return o1.getOrder().compareTo(o2.getOrder());
            }
        });

        lvChoices.setAdapter(adapterChoice);

        btNewChoice = findViewById(R.id.btnAddChoice);
        btNewChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog();
            }
        });

        edtTitle = findViewById(R.id.edtQuestionTitle);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();
                if (i != null) {
                    PresSession session = (PresSession) i.getSerializableExtra("session");

                    ChoicesQuestion question = new ChoicesQuestion();
                    question.setTitle(edtTitle.getText().toString());
                    question.setAlternatives(alternatives);
                    question.setSession(session);

                    new SaveQuestionAsyncTask().execute(question);
                }

            }
        });

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    public void onPositive(Alternative alternative) {
        adapterChoice.add(alternative);
        Toast.makeText(this, "Alternative added", Toast.LENGTH_LONG).show();
    }

    private void showChoiceDialog() {
        NewChoiceDialog dialog = new NewChoiceDialog();
        dialog.show(getSupportFragmentManager(), "newChoice");
    }

    private class SaveQuestionAsyncTask extends AsyncTask<ChoicesQuestion, Void, ChoicesQuestion> {

        private Exception e;

        @Override
        protected ChoicesQuestion doInBackground(ChoicesQuestion... choicesQuestions) {
            try {
                ChoicesQuestion question = ChoicesQuestionWebDAO.getInstance().create(choicesQuestions[0]);
                return question;
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
        protected void onPostExecute(ChoicesQuestion choicesQuestion) {
            if (e != null) {
                Toast.makeText(NewChoiceQuestionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent();
                i.putExtra("question", choicesQuestion);
                setResult(SessionQuestionsFragment.RES_NEW_CHOICE_QUESTION, i);
                finish();
            }
        }
    }
}
