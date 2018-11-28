package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitorgreati.presapp.config.AppUtils;
import com.example.vitorgreati.presapp.dao.impl.PresentationWebDAO;
import com.example.vitorgreati.presapp.dao.interfaces.PresentationDAO;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.fragments.DashPresentationsFragment;
import com.example.vitorgreati.presapp.model.Presentation;

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
                String title = edtTitle.getText().toString();
                String description = edtDescription.getText().toString();

                Presentation newPres = new Presentation(title, description);
                newPres.setUser(AppUtils.getLoggedUser(NewPresActivity.this));

                new CreatePresAsyncTask().execute(newPres);
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

    private class CreatePresAsyncTask extends AsyncTask<Presentation, Integer, Presentation> {

        private Exception exception = null;

        @Override
        protected Presentation doInBackground(Presentation... press) {
            Presentation retPres  = null;
            try {
                retPres = PresentationWebDAO.getInstance().create(press[0]);
            } catch (WebException e) {
                exception = e;
                return null;
            }
            return retPres;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Presentation pres) {
            super.onPostExecute(pres);
            if (exception != null) {
                Toast.makeText(NewPresActivity.this, exception.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(NewPresActivity.this, "Presentation registered", Toast.LENGTH_LONG);
                Intent i = new Intent();
                i.putExtra("pres", pres);
                setResult(DashPresentationsFragment.RES_NEW_PRES, i);
                finish();
            }
        }
    }

}
