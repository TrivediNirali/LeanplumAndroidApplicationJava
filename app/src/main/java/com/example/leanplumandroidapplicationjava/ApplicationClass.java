package com.example.leanplumandroidapplicationjava;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.annotations.Parser;


public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("test log", "u fool");
        Leanplum.setApplicationContext(this);
        Parser.parseVariables(this);
        Leanplum.setLogLevel(com.leanplum.internal.Log.Level.DEBUG);

        //  For session lifecyle tracking. Must be called if you do not extend LeanplumApplication Class
        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        // Insert your API keys here.
        if (BuildConfig.DEBUG) {
            Leanplum.setAppIdForDevelopmentMode("app_7rES7iqzXiBbDW4KvyB928lgqxZzv6ZqN2WHIQIdS0A","dev_hRcCzQRU78zJhpmR7ZSXS2vAQxRd44nQA3h11b6Wsbw");
        } else {
            Leanplum.setAppIdForProductionMode("app_7rES7iqzXiBbDW4KvyB928lgqxZzv6ZqN2WHIQIdS0A", "prod_SCaHcX0KHY7Zf8Sp7zjRoTbLSC1UDl9VLEOY1pPgSGE");
        }

        // This will only run once per session, even if the activity is restarted.
      //  Leanplum.setUserId("user1234");
       Leanplum.start(this);
        createNotificationChannel();
      //  Log.v("this" , "jghjfhgfhgfhgfhgfhjg");
    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           // CharSequence name = getString(R.string.channel_name);
            // String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_MAX;
            NotificationChannel channel = new NotificationChannel("1", "testChannel", importance);
           // channel.setDescription(description);
            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
