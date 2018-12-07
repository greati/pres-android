package com.example.vitorgreati.presapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.PresSession;
import com.example.vitorgreati.presapp.model.Presentation;

public class SessionEditFragment extends Fragment {

    private Button btCreate, btnCancel;
    private EditText edtLocal, edtDateTime;

    private PresSession session;

    public static Fragment newInstance(PresSession s) {
        Fragment instance = new SessionEditFragment();
        Bundle args = new Bundle();
        args.putSerializable("session", s);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = (PresSession) getArguments().getSerializable("session");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_edit_session, container, false);

        edtLocal = v.findViewById(R.id.edtLocal);
        edtLocal.setText(session.getLocation().getDescription());

        edtDateTime = v.findViewById(R.id.edtDateTime);
        edtDateTime.setText(session.getDateTime().toString());

        btnCancel = v.findViewById(R.id.btnCancel);
        btnCancel.setVisibility(View.INVISIBLE);

        btCreate = v.findViewById(R.id.btnConfirm);
        btCreate.setText("Save");
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Not yet supported", Toast.LENGTH_LONG).show();
            }
        });
        return v;
    }
}
