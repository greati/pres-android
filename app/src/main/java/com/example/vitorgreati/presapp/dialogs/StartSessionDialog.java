package com.example.vitorgreati.presapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vitorgreati.presapp.R;

public class StartSessionDialog extends DialogFragment {

    private TextView tvSessionCode;
    private Button btEnter, btCancel;

    private OnSessionStartListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (OnSessionStartListener) context;
        } catch (Exception e) {
            throw new ClassCastException("Pass a OnSessionStartListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Start a session");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.start_session_dialog, null);

        tvSessionCode = v.findViewById(R.id.tvSessionCode);

        btEnter = v.findViewById(R.id.btStartSession);
        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onStartSession();
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

    public interface OnSessionStartListener {
        void onStartSession();
    }
}
