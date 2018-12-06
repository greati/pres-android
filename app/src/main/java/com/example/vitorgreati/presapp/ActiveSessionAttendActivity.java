package com.example.vitorgreati.presapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vitorgreati.presapp.config.AppUtils;
import com.example.vitorgreati.presapp.dao.impl.PresSessionWebDAO;
import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Participation;
import com.example.vitorgreati.presapp.services.PresFirebaseMessagingService;

import java.util.Date;

public class ActiveSessionAttendActivity extends AppCompatActivity {

    private Participation participation;
    private TextView tvSessionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_session_attend);

        Intent i = getIntent();
        if (i != null) {
            participation = (Participation) i.getSerializableExtra("part");
        }

        tvSessionName = findViewById(R.id.tvSessionName);

        tvSessionName.setText(participation.getSession().getPresentation().getTitle());

        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String msg = intent.getStringExtra("question_id");
                Toast.makeText(ActiveSessionAttendActivity.this, msg, Toast.LENGTH_LONG).show();
            }
        }, new IntentFilter(PresFirebaseMessagingService.OPEN_QUESTION_ACTION));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Open session").setIcon(android.R.drawable.ic_delete)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new QuitSessionAsyncTask().execute(participation.getSession().getId());
                return true;
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }

    private class QuitSessionAsyncTask extends AsyncTask<String, Void, Void> {

        private Exception e = null;

        @Override
        protected Void doInBackground(String... strings) {

            try {
                PresSessionWebDAO.getInstance().quit(strings[0], AppUtils.getLoggedUser(ActiveSessionAttendActivity.this), PresFirebaseMessagingService.getToken(ActiveSessionAttendActivity.this));
            } catch (UserNotFoundException | WebException e1) {
                this.e = e1;
                e1.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (e != null) {
                Toast.makeText(ActiveSessionAttendActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                finish();
            }
        }
    }
}
