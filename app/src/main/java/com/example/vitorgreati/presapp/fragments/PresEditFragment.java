package com.example.vitorgreati.presapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.Presentation;
import com.example.vitorgreati.presapp.model.User;

public class PresEditFragment extends Fragment {

    private Presentation pres;

    private EditText edtTitle, edtDescription;

    private Button btnCreate;

    public static Fragment newInstance(Presentation p) {
        Fragment instance = new PresEditFragment();
        Bundle args = new Bundle();
        args.putSerializable("pres", p);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.pres = (Presentation) getArguments().getSerializable("pres");

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_new_pres, container, false);

        edtTitle = v.findViewById(R.id.edtTitle);
        edtTitle.setText(pres.getTitle());

        edtDescription = v.findViewById(R.id.edtDescription);
        edtDescription.setText(pres.getDescription());

        btnCreate = v.findViewById(R.id.btnConfirm);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Should save", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }
}
