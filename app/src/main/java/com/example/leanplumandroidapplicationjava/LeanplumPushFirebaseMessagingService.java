package com.example.leanplumandroidapplicationjava;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.leanplum.LeanplumFirebaseServiceHandler;

public class LeanplumPushFirebaseMessagingService extends FirebaseMessagingService {
    private final LeanplumFirebaseServiceHandler handler = new LeanplumFirebaseServiceHandler();

    @Override
    public void onCreate() {
        super.onCreate();
        handler.onCreate(getApplicationContext());
        Log.v("firebasemessage", "firebasemessage");
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        handler.onNewToken(token, getApplicationContext());
    }

    /**
     * Called when a message is received. This is also called when a notification message is received
     * while the app is in the foreground.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        handler.onMessageReceived(remoteMessage, getApplicationContext());
    }
}
