package com.example.vitorgreati.presapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.vitorgreati.presapp.adapters.AdapterQuestionSessionActive;
import com.example.vitorgreati.presapp.config.AppUtils;
import com.example.vitorgreati.presapp.dao.impl.PresSessionWebDAO;
import com.example.vitorgreati.presapp.dialogs.StartSessionDialog;
import com.example.vitorgreati.presapp.exception.UnauthorizedOperationException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.ChoicesQuestion;
import com.example.vitorgreati.presapp.model.PresSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActiveSessionPresenterActivity extends AppCompatActivity {

    private RecyclerView rvQuestions;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;

    private PresSession session;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Open session").setIcon(android.R.drawable.ic_media_pause)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                new CloseSessionAsyncTask().execute(session, new Date());

                return true;
            }
        });

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_session_presenter);

        rvQuestions = findViewById(R.id.rvQuestions);

        layoutManager = new LinearLayoutManager(this);
        rvQuestions.setLayoutManager(layoutManager);

        List<ChoicesQuestion> questions = new ArrayList<>();
        questions.add(new ChoicesQuestion("Question 1", null));
        questions.add(new ChoicesQuestion("Question 2", null));

        adapter = new AdapterQuestionSessionActive(questions);

        Intent i = getIntent();

        if (i != null) {
            this.session = (PresSession) i.getSerializableExtra("session");
        }

        rvQuestions.setAdapter(adapter);

    }

    private class CloseSessionAsyncTask extends AsyncTask<Object,Void,Void> {

        private Exception e = null;

        @Override
        protected Void doInBackground(Object... sess) {

            try {
                PresSessionWebDAO.getInstance().close((PresSession) sess[0], AppUtils.getLoggedUser(ActiveSessionPresenterActivity.this), (Date) sess[1]);
            } catch (WebException e) {
                this.e = e;
            } catch (UnauthorizedOperationException e1) {
                this.e = e1;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (e != null) {
                Toast.makeText(ActiveSessionPresenterActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                finish();
            }
        }
    }
}
