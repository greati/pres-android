package com.example.vitorgreati.presapp.config;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.vitorgreati.presapp.model.User;

public class AppUtils {

    public synchronized static User getLoggedUser(Context ctx) {

        final SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ctx);

        String id = sharedPref.getString("loggedUserId", null);

        if (id != null) {
            User u = new User();
            u.setId(id);
            u.setName(sharedPref.getString("loggedUserName", null));
            u.setEmail(sharedPref.getString("loggedUserEmail", null));
            return u;
        }

        return null;
    }


}
