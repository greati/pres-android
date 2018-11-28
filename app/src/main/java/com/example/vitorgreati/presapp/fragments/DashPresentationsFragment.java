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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.vitorgreati.presapp.NewPresActivity;
import com.example.vitorgreati.presapp.PresManagerActivity;
import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.adapters.AdapterDashPres;
import com.example.vitorgreati.presapp.config.AppUtils;
import com.example.vitorgreati.presapp.dao.impl.PresentationWebDAO;
import com.example.vitorgreati.presapp.exception.WebException;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class DashPresentationsFragment extends Fragment implements AdapterDashPres.OnItemClickListener {

    public static final int REQ_NEW_PRES = 1;
    public static final int RES_NEW_PRES = 1;

    private User user;

    private RecyclerView recyclerPres;
    private RecyclerView.Adapter adapterPres;
    private RecyclerView.LayoutManager layoutManagerPres;

    private FloatingActionButton fabAddPres;

    public static Fragment newInstance(User u) {
        Fragment instance = new DashPresentationsFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", u);
        instance.setArguments(args);
        return instance;
    }

    private List<Presentation> presentations;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dash_presents_fragment, container, false);

        recyclerPres = v.findViewById(R.id.recyclerDashPres);

        recyclerPres.setHasFixedSize(true);

        layoutManagerPres = new LinearLayoutManager(container.getContext());
        recyclerPres.setLayoutManager(layoutManagerPres);

        this.presentations = new ArrayList<>();
        presentations.add(new Presentation("oi","ola"));

        adapterPres = new AdapterDashPres(presentations, this);
        recyclerPres.setAdapter(adapterPres);

        fabAddPres = v.findViewById(R.id.fabAddPres);
        fabAddPres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), NewPresActivity.class);
                startActivityForResult(i, REQ_NEW_PRES);
            }
        });

        new ListPresAsyncTask().execute();

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(resultCode) {
            case RES_NEW_PRES:
                Presentation p = (Presentation) data.getSerializableExtra("pres");
                presentations.add(p);
                adapterPres.notifyDataSetChanged();
                break;
        }

    }

    @Override
    public void onItemClick(int pos, View v) {
        Presentation clickedPres = ((AdapterDashPres) adapterPres).getPresentations().get(pos);

        Intent i = new Intent(getContext(), PresManagerActivity.class);
        i.putExtra("pres", clickedPres);
        startActivity(i);
    }

    private class ListPresAsyncTask extends AsyncTask<Void, Void, List<Presentation>> {

        private Exception e = null;

        @Override
        protected List<Presentation> doInBackground(Void... voids) {

            try {
                return PresentationWebDAO.getInstance().list(AppUtils.getLoggedUser(getActivity()));
            } catch (WebException e1) {
                e = e1;
                return null;
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Presentation> pres) {
            if (e != null) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
            } else {
                DashPresentationsFragment.this.presentations.clear();
                presentations.addAll(pres);
                DashPresentationsFragment.this.adapterPres.notifyDataSetChanged();
            }
        }
    }
}
