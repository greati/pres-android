package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NewSessionActivity extends AppCompatActivity {

    private TextView tvPresTitle;
    private Button btCreate, btCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);

        getSupportActionBar().setTitle(R.string.new_session_title);

        tvPresTitle = findViewById(R.id.tvPresTitle);

        Intent i = getIntent();

        if (i != null) {
            String presTitle = i.getStringExtra("presTitle");
            tvPresTitle.setText(presTitle);
        }

        btCancel = findViewById(R.id.btnCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btCreate = findViewById(R.id.btnConfirm);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
