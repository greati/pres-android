package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vitorgreati.presapp.dao.impl.PresSessionWebDAO;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.fragments.PresSessionsManFragment;
import com.example.vitorgreati.presapp.model.Location;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;

import java.util.Date;

public class NewSessionActivity extends AppCompatActivity {

    private TextView tvPresTitle;
    private EditText edtLocal, edtDateTime;
    private Button btCreate, btCancel;

    private Presentation pres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_session);

        getSupportActionBar().setTitle(R.string.new_session_title);

        tvPresTitle = findViewById(R.id.tvPresTitle);

        Intent i = getIntent();

        if (i != null) {
            this.pres = (Presentation) i.getSerializableExtra("pres");
            tvPresTitle.setText(pres.getTitle());
        }

        edtLocal = findViewById(R.id.edtLocal);
        edtDateTime = findViewById(R.id.edtDateTime);

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

                PresSession s = new PresSession();

                s.setLocation(new Location(edtLocal.getText().toString(), -1.0,-1.0)); //TODO take location from map or similar
                s.setDateTime(new Date()); // TODO take right date from date picker
                s.setPresentation(pres);

                new NewSessionAsyncTask().execute(s);
            }
        });
    }

    private class NewSessionAsyncTask extends AsyncTask<PresSession, Void, PresSession> {

        private Exception e;

        @Override
        protected PresSession doInBackground(PresSession... presSessions) {
            try {
                PresSession s = PresSessionWebDAO.getInstance().create(presSessions[0]);
                return s;
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
        protected void onPostExecute(PresSession s) {
            super.onPostExecute(s);
            Intent i = new Intent();
            i.putExtra("session", s);
            setResult(PresSessionsManFragment.RES_NEW_SESSION, i);
            finish();
        }
    }
}
