package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitorgreati.presapp.dao.impl.UserWebDAO;
import com.example.vitorgreati.presapp.dao.interfaces.UserDAO;
import com.example.vitorgreati.presapp.exception.DuplicateUsernameException;
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

                EditText edtName = findViewById(R.id.edtName);
                EditText edtEmail = findViewById(R.id.edtEmail);
                EditText edtPassword = findViewById(R.id.edtPassword);
                EditText edtRePassword = findViewById(R.id.edtRePassword);

                User u = new User();
                u.setName(edtName.getText().toString());
                u.setEmail(edtEmail.getText().toString());
                u.setPassword(edtPassword.getText().toString());

                if (u.getPassword().equals(edtRePassword.getText().toString()))
                    new RegisterAsyncTask().execute(u);
                else
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Passwords don't match", Toast.LENGTH_LONG).show();
                        }
                    });
            }
        });

    }

    private class RegisterAsyncTask extends AsyncTask<User, Void, User> {
        @Override
        protected User doInBackground(User... users) {
            UserDAO userDao = UserWebDAO.getInstance();
            try {
                userDao.create(users[0]);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_LONG).show();
                    }
                });
            } catch (WebException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Fail to register due to network issues", Toast.LENGTH_LONG).show();
                    }
                });
            } catch (DuplicateUsernameException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Invalid username", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return users[0];
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            Intent i = new Intent();
            i.putExtra("email", user.getEmail());
            setResult(LoginActivity.REGISTRATION_SUCCESS, i);
            finish();
        }
    }
}
