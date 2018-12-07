package com.example.vitorgreati.presapp.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.CallAudioState;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitorgreati.presapp.ActiveSessionAttendActivity;
import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.adapters.AdapterDashPres;
import com.example.vitorgreati.presapp.adapters.AdapterParticipation;
import com.example.vitorgreati.presapp.adapters.AdapterSession;
import com.example.vitorgreati.presapp.config.AppUtils;
import com.example.vitorgreati.presapp.dao.impl.PresSessionWebDAO;
import com.example.vitorgreati.presapp.dialogs.EnterSessionDialog;
import com.example.vitorgreati.presapp.exception.UserNotFoundException;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.interfaces.DialogStarter;
import com.example.vitorgreati.presapp.model.Location;
import com.example.vitorgreati.presapp.model.Participation;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;
import com.example.vitorgreati.presapp.services.PresFirebaseMessagingService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DashAttendsFragment extends Fragment
        implements AdapterParticipation.OnItemClickListener, EnterSessionDialog.OnSessionEnterListener {

    private RecyclerView recyclerSession;
    private RecyclerView.Adapter adapterParts;
    private RecyclerView.LayoutManager layoutManagerSession;

    private DialogStarter dialogStarter;

    private List<Participation> parts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.add(0,0,0,"Enter session")
                .setIcon(R.drawable.ic_tab_add)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                EnterSessionDialog dialog = new EnterSessionDialog();
                dialog.setTargetFragment(DashAttendsFragment.this, 1);
                dialogStarter.startDialog(dialog);

                return true;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            dialogStarter = (DialogStarter) context;
        } catch(Exception e) {
            throw new ClassCastException("Pass a DialogStarter");
        }
    }

    public static Fragment newInstance(User u) {
        Fragment instance = new DashAttendsFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", u);
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

        parts = new ArrayList<>();

        adapterParts = new AdapterParticipation(parts, getContext(),this);
        recyclerSession.setAdapter(adapterParts);

        new ListAttendsAsyncTask().execute(AppUtils.getLoggedUser(getContext()));

        return v;
    }

    @Override
    public void onItemClick(int p, View v) {

    }

    @Override
    public void onEnterSession(String sessionCode) {
        new EnterSessionAsyncTask().execute(sessionCode);
    }

    private class EnterSessionAsyncTask extends AsyncTask<String, Void, Participation> {

        private Exception e;

        @Override
        protected Participation doInBackground(String... strings) {

            String sessionCode = strings[0];

            try {
                return PresSessionWebDAO.getInstance().participate(sessionCode, AppUtils.getLoggedUser(getContext()), PresFirebaseMessagingService.getToken(getContext()));
            } catch (UserNotFoundException | WebException e) {
                e.printStackTrace();
                this.e = e;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Participation part) {
            super.onPostExecute(part);

            if (e != null) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                Intent i = new Intent(getActivity(), ActiveSessionAttendActivity.class);
                i.putExtra("part", part);
                startActivity(i);
            }
        }
    }

    private class ListAttendsAsyncTask extends AsyncTask<User, Void, List<Participation>> {

        private Exception e;

        @Override
        protected List<Participation> doInBackground(User... users) {

            try {
                List<Participation> parts =
                        PresSessionWebDAO.getInstance().listParticipations(users[0]);
                return parts;
            } catch (WebException e1) {
                this.e = e1;
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Participation> participations) {
            super.onPostExecute(participations);

            if (e != null){
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                parts.clear();
                parts.addAll(participations);
                adapterParts.notifyDataSetChanged();
            }

        }
    }

}
