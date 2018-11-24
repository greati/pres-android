package com.example.vitorgreati.presapp.fragments;

import android.app.Dialog;
import android.content.Context;
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

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.adapters.AdapterDashPres;
import com.example.vitorgreati.presapp.adapters.AdapterSession;
import com.example.vitorgreati.presapp.dialogs.EnterSessionDialog;
import com.example.vitorgreati.presapp.interfaces.DialogStarter;
import com.example.vitorgreati.presapp.model.Location;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DashAttendsFragment extends Fragment
        implements AdapterSession.OnItemClickListener, EnterSessionDialog.OnSessionEnterListener {

    private RecyclerView recyclerSession;
    private RecyclerView.Adapter adapterSession;
    private RecyclerView.LayoutManager layoutManagerSession;

    private DialogStarter dialogStarter;

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

        // create a list for testing

        Presentation p1 = new Presentation("Pres 1", "Desc 1");
        Presentation p2 = new Presentation("Pres 2", "Desc 2");

        PresSession s1 = new PresSession(new Location("IMD, UFRN", 1.0, 2.0), new Date(), p1);
        PresSession s2 = new PresSession(new Location("SEBRAE-RN", 1.0, 2.0), new Date(), p2);


        List<PresSession> testList = new ArrayList<>();
        testList.add(s1);
        testList.add(s2);

        adapterSession = new AdapterSession(testList, this);
        recyclerSession.setAdapter(adapterSession);

        return v;
    }

    @Override
    public void onItemClick(int p, View v) {

    }

    @Override
    public void onEnterSession() {
        Toast.makeText(getActivity(), "Enter a session", Toast.LENGTH_LONG).show();
    }

}
