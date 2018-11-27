package com.example.vitorgreati.presapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitorgreati.presapp.dao.impl.PresentationWebDAO;
import com.example.vitorgreati.presapp.dao.interfaces.PresentationDAO;
import com.example.vitorgreati.presapp.exception.WebException;
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

                try {
                    Presentation retPres = PresentationWebDAO.getInstance().create(newPres);

                    Toast.makeText(NewPresActivity.this, "Presentation registered", Toast.LENGTH_LONG);

                    //TODO return presentation as result

                    finish();
                } catch (WebException e) {
                    Toast.makeText(NewPresActivity.this, "Error on API access", Toast.LENGTH_LONG);
                }
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
