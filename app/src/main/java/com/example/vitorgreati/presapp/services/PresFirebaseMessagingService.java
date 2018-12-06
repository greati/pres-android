package com.example.vitorgreati.presapp.services;

import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.vitorgreati.presapp.config.AppUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PresFirebaseMessagingService extends FirebaseMessagingService {

    private final LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);

    public static final String OPEN_QUESTION_ACTION = "com.example.vitorgreati.presapp.OPEN_QUESTION_ACTION";

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0) {

            switch (remoteMessage.getData().get("type")) {
                case "open_question":
                    Intent i = new Intent(OPEN_QUESTION_ACTION);
                    i.putExtra("question_id",remoteMessage.getData().get("question_id"));
                    localBroadcastManager.sendBroadcast(i);
                    break;
            }

        }


        Log.d("pres","Mensagem recebida!");

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.i("pres", "A new token was generated!");
        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fb", s).apply();
    }

    public static String getToken(Context context) {
        return context.getSharedPreferences("_", MODE_PRIVATE).getString("fb", "empty");
    }

}
