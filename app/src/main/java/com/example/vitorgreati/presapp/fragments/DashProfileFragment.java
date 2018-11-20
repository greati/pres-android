package com.example.vitorgreati.presapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.User;

public class DashProfileFragment extends Fragment {

    private User user;

    public static Fragment newInstance(User u) {
        DashProfileFragment instance = new DashProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable("user", u);
        instance.setArguments(args);
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.user = (User) getArguments().getSerializable("user");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dash_profile_fragment, container, false);

        TextView tvNome = v.findViewById(R.id.tvName);
        TextView tvEmail = v.findViewById(R.id.tvEmail);

        tvNome.setText(this.user.getName());
        tvEmail.setText(this.user.getEmail());

        return v;
    }
}
