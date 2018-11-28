package com.example.vitorgreati.presapp.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitorgreati.presapp.NewSessionActivity;
import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.SessionManagerActivity;
import com.example.vitorgreati.presapp.adapters.AdapterSession;
import com.example.vitorgreati.presapp.dao.impl.PresSessionWebDAO;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Location;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PresSessionsManFragment extends Fragment implements AdapterSession.OnItemClickListener {

    public static final int REQ_NEW_SESSION = 1;
    public static final int RES_NEW_SESSION = 1;

    private FloatingActionButton fabAddSession;

    private RecyclerView recyclerSession;
    private RecyclerView.Adapter adapterSession;
    private RecyclerView.LayoutManager layoutManagerSession;

    private Presentation pres;

    private List<PresSession> sessions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.pres = (Presentation) getArguments().getSerializable("pres");
    }

    public static Fragment newInstance(Presentation p) {
        Fragment instance = new PresSessionsManFragment();
        Bundle args = new Bundle();
        args.putSerializable("pres", p);
        instance.setArguments(args);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.dash_attends_fragment, container, false);

        recyclerSession = v.findViewById(R.id.recyclerDashAttends);

        recyclerSession.setHasFixedSize(true);

        layoutManagerSession = new LinearLayoutManager(container.getContext());
        recyclerSession.setLayoutManager(layoutManagerSession);

        sessions = new ArrayList<>();

        adapterSession = new AdapterSession(sessions, this);
        recyclerSession.setAdapter(adapterSession);

        fabAddSession = v.findViewById(R.id.fabAddAttends);
        fabAddSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), NewSessionActivity.class);
                i.putExtra("pres", pres);
                startActivityForResult(i, REQ_NEW_SESSION);
            }
        });

        new ListSessionsAsyncTask().execute(pres);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RES_NEW_SESSION:
                PresSession s = (PresSession) data.getSerializableExtra("session");
                sessions.add(s);
                adapterSession.notifyDataSetChanged();
                break;

        }
    }

    @Override
    public void onItemClick(int p, View v) {
        PresSession s = ((AdapterSession) adapterSession).getSessions().get(p);
        Intent i = new Intent(getContext(), SessionManagerActivity.class);
        i.putExtra("session", s);
        startActivity(i);
    }

    private class ListSessionsAsyncTask extends AsyncTask<Presentation, Void, List<PresSession>> {

        private Exception e;

        @Override
        protected List<PresSession> doInBackground(Presentation... press) {
            try {
                return PresSessionWebDAO.getInstance().list(press[0]);
            } catch (WebException e1) {
                e = e1;
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<PresSession> presSessions) {
            super.onPostExecute(presSessions);
            if (e != null) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                sessions.clear();
                sessions.addAll(presSessions);
                adapterSession.notifyDataSetChanged();
            }
        }
    }
}
