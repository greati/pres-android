package com.example.vitorgreati.presapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.vitorgreati.presapp.R;
import com.example.vitorgreati.presapp.model.Alternative;

public class NewChoiceDialog extends DialogFragment {

    private EditText edtChoice;
    private Spinner spOrder;

    private OnPositiveListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof OnPositiveListener))
            throw new RuntimeException("NewChoiceDialog needs a OnPositiveListener context");

        this.listener = (OnPositiveListener) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.new_choice_dialog, null);

        edtChoice = v.findViewById(R.id.edtChoiceContent);

        spOrder = v.findViewById(R.id.spOrder);

        Integer[] orders = new Integer[]{1,2,3,4};
        ArrayAdapter<Integer> spAdapter = new ArrayAdapter<Integer>(getContext(),
                android.R.layout.simple_spinner_item, orders);
        spOrder.setAdapter(spAdapter);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.dialog_new_choice_title);
        builder.setView(v);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Alternative alternative = new Alternative(edtChoice.getText().toString(),
                        (Integer) spOrder.getSelectedItem());
                listener.onPositive(alternative);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return builder.create();
    }

    public interface OnPositiveListener {
        void onPositive(Alternative alternative);
    }
}
