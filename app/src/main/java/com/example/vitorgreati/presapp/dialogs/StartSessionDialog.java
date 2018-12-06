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
import com.example.vitorgreati.presapp.model.PresSession;

public class StartSessionDialog extends DialogFragment {

    private TextView tvSessionCode;
    private Button btEnter, btCancel;

    private OnSessionStartListener listener;

    private PresSession session;

    public static StartSessionDialog newInstance(PresSession session){
        StartSessionDialog dialog = new StartSessionDialog();

        Bundle args = new Bundle();
        args.putSerializable("session", session);
        dialog.setArguments(args);

        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (OnSessionStartListener) context;
        } catch (Exception e) {
            throw new ClassCastException("Pass a OnSessionStartListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.session = (PresSession) getArguments().getSerializable("session");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Start a session");

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.start_session_dialog, null);

        tvSessionCode = v.findViewById(R.id.tvSessionCode);
        tvSessionCode.setText(this.session.getCode());

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
