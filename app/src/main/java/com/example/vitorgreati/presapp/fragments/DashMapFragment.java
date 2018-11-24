package com.example.vitorgreati.presapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.vitorgreati.presapp.model.User;

public class DashMapFragment extends Fragment {

    public static Fragment newInstance() {
        Fragment instance = new DashMapFragment();
        Bundle args = new Bundle();
        instance.setArguments(args);
        return instance;
    }

}
