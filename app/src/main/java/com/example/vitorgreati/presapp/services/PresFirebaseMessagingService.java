package com.example.vitorgreati.presapp.services;

import android.content.Context;
import android.util.Log;

import com.example.vitorgreati.presapp.config.AppUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PresFirebaseMessagingService extends FirebaseMessagingService {



    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

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
