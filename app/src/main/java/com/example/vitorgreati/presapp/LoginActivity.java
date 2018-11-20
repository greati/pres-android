package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitorgreati.presapp.dao.impl.UserWebDAO;
import com.example.vitorgreati.presapp.dao.interfaces.UserDAO;
import com.example.vitorgreati.presapp.exception.AuthenticationException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.User;

import java.util.HashSet;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    public static final int REQ_CODE_REGISTER = 0;
    public static final int REGISTRATION_SUCCESS = 10;

    private Button btRegister, btLogin;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btRegister = findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), RegisterActivity.class);
                startActivityForResult(i, REQ_CODE_REGISTER);
            }
        });

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btLogin = findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();

                User u = new User();
                u.setEmail(email);
                u.setPassword(password);

                new LoginAsyncTask().execute(u);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(resultCode) {
            case REGISTRATION_SUCCESS:
                String email = data.getStringExtra("email");
                edtEmail.setText(email);
                edtPassword.requestFocus();
                break;
        }
    }

    private class LoginAsyncTask extends AsyncTask<User, Void, User> {

        @Override
        protected User doInBackground(User... users) {

            UserDAO dao = UserWebDAO.getInstance();

            User u = users[0];

            if (u != null) {
                try {
                    User authenticatedUser = dao.authenticate(u.getEmail(), u.getPassword());
                    return authenticatedUser;
                } catch (AuthenticationException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                        Toast.makeText(getBaseContext(), "Authentication failed", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (WebException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getBaseContext(), "Network fail", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            if (user != null) {

                final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                SharedPreferences.Editor editor = sharedPref.edit();

                Set<String> userInfo = new HashSet<>();
                userInfo.add(user.getId());
                editor.putString("loggedUserId", String.valueOf(user.getId()));
                editor.putString("loggedUserEmail", String.valueOf(user.getEmail()));
                editor.putString("loggedUserName", String.valueOf(user.getName()));
                editor.apply();

                Intent i = new Intent(getBaseContext(), DashboardActivity.class);
                startActivity(i);
            }
        }
    }
}
