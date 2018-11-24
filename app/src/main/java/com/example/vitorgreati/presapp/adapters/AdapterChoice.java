package com.example.vitorgreati.presapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.Alternative;

import java.util.Comparator;
import java.util.List;

public class AdapterChoice extends ArrayAdapter<Alternative> {

    public AdapterChoice(@NonNull Context context, @NonNull List<Alternative> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Alternative a = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.choice_item, parent, false);
        }

        TextView edtChoiceContent = convertView.findViewById(R.id.tvChoiceContent);

        edtChoiceContent.setText(a.getText());

        return convertView;
    }

}
