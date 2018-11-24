package com.example.vitorgreati.presapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vitorgreati.presapp.adapters.AdapterChoice;
import com.example.vitorgreati.presapp.dialogs.NewChoiceDialog;
import com.example.vitorgreati.presapp.model.Alternative;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NewChoiceQuestionActivity extends AppCompatActivity implements NewChoiceDialog.OnPositiveListener {

    private Button btNewChoice;
    private ListView lvChoices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_choice_question);

        lvChoices = findViewById(R.id.lvChoices);

        List<Alternative> alternativeList = new ArrayList<>();
        alternativeList.add(new Alternative("Teste",2));
        alternativeList.add(new Alternative("Olá", 1));


        AdapterChoice adapterChoice = new AdapterChoice(this, alternativeList);

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
    }

    @Override
    public void onPositive(Alternative alternative) {
        Toast.makeText(this, alternative.getText(), Toast.LENGTH_LONG).show();
    }

    private void showChoiceDialog() {
        NewChoiceDialog dialog = new NewChoiceDialog();
        dialog.show(getSupportFragmentManager(), "newChoice");
    }
}
