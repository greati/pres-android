package com.example.vitorgreati.presapp.services;

import android.util.Log;

import com.example.vitorgreati.presapp.config.AppUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PresFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("pres","Mensagem recebida!");

    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        // close the session where the user is active!
    }
}
