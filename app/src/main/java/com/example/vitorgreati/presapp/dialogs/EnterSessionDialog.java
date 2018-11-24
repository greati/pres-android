package com.example.vitorgreati.presapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;

public class EnterSessionDialog extends DialogFragment {

    private EditText edtSessionCode;
    private Button btEnter, btCancel;

    private OnSessionEnterListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (getTargetFragment() != null) {
                this.listener = (OnSessionEnterListener) getTargetFragment();
            } else {
                this.listener = (OnSessionEnterListener) context;
            }
        } catch(Exception e) {
            throw new ClassCastException("Pass a OnSessionEnterListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Start a session");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.enter_session_dialog, null);

        edtSessionCode = v.findViewById(R.id.edtSessionCode);

        btEnter = v.findViewById(R.id.btStartSession);
        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEnterSession();
            }
        });

        btCancel = v.findViewById(R.id.btCancel);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        builder.setView(v);

        return builder.create();
    }

    public interface OnSessionEnterListener {
        void onEnterSession();
    }
}
