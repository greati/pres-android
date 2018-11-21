package com.example.vitorgreati.presapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPresActivity extends AppCompatActivity {

    private Button btConfirm, btCancel;

    private EditText edtTitle, edtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pres);

        getSupportActionBar().setTitle(R.string.new_pres_title);

        edtTitle = findViewById(R.id.edtTitle);
        edtDescription = findViewById(R.id.edtDescription);

        btConfirm = findViewById(R.id.btnConfirm);
        btConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btCancel = findViewById(R.id.btnCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
