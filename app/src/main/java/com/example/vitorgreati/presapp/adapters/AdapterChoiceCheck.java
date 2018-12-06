package com.example.vitorgreati.presapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.Alternative;

import java.util.List;

public class AdapterChoiceCheck extends ArrayAdapter<Alternative> {

    public AdapterChoiceCheck(Context context, int resource, List<Alternative> alternatives) {
        super(context,0, alternatives);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Alternative a = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.check_item_checkbox, parent, false);
        }

        CheckBox cb = convertView.findViewById(R.id.cbAlternative);

        cb.setText(a.getText());

        return convertView;
    }
}
