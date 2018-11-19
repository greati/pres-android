package com.example.vitorgreati.presapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitorgreati.presapp.dao.impl.UserWebDAO;
import com.example.vitorgreati.presapp.dao.interfaces.UserDAO;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.User;

public class RegisterActivity extends AppCompatActivity {

    private Button btnCancel, btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().setTitle(R.string.register_title);

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                setResult(RESULT_CANCELED);
            }
        });

        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDAO userDao = UserWebDAO.getInstance();

                EditText edtName = findViewById(R.id.edtName);
                EditText edtEmail = findViewById(R.id.edtEmail);
                EditText edtPassword = findViewById(R.id.edtPassword);

                User u = new User();
                u.setName(edtName.getText().toString());
                u.setEmail(edtEmail.getText().toString());
                u.setPassword(edtPassword.getText().toString());

                try {
                    userDao.create(u);
                    Toast.makeText(v.getContext(), "Success!", Toast.LENGTH_LONG).show();
                } catch (WebException e) {
                    //Toast.makeText(v.getContext(), "Fail to register due to network issues", Toast.LENGTH_LONG).show();
                    Log.i("apierr", e.getMessage());
                }
            }
        });

    }
}
