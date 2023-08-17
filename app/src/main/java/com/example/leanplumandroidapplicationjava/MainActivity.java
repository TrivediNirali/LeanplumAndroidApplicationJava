package com.example.leanplumandroidapplicationjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumApplication;
import com.leanplum.LeanplumInbox;
import com.leanplum.LeanplumInboxMessage;
import com.leanplum.callbacks.InboxSyncedCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


// For tracking user sessions.

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(R.layout.activity_inboxlist);

       // CleverTapAPI clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        LeanplumApplication leanplum = LeanplumApplication.getInstance();


        Button btnlogin=findViewById(R.id.login);
        Button btnpushprofile=findViewById(R.id.pushprofile);
        Button btnpushevent=findViewById(R.id.pushevent);
        Button btnpusheventproperty=findViewById(R.id.pusheventwithprperty);
        Button btnpushnotification=findViewById(R.id.pushnotification);
        Button btninapp=findViewById(R.id.inapp);
        Button btnappinbox=findViewById(R.id.appinbox);
        Button btnappinboxshow=findViewById(R.id.appinboxshow);
        EditText etidentity=findViewById(R.id.identity);
        EditText etname=findViewById(R.id.name);
        EditText etemail=findViewById(R.id.editTextTextEmailAddress);
        EditText etmobile=findViewById(R.id.editTextPhone);
       // ListView lvinbox=findViewById(R.id.listView);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String identity = etidentity.getText().toString();
                String name = etname.getText().toString();
                String email = etemail.getText().toString();
                String mobile = etmobile.getText().toString();
                Toast.makeText(MainActivity.this, "Hi" + name, Toast.LENGTH_SHORT).show();

                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Enter Identity", identity);
                profileUpdate.put("Name", name);
                profileUpdate.put("Enter Email", email);
                profileUpdate.put("Enter Mobile", mobile);
                Leanplum.setUserId("user1234");
                Leanplum.setUserAttributes(profileUpdate);
               Leanplum.start(getApplicationContext(), profileUpdate);
            }
            //Leanplum.track("Launch");
        });
        btnpusheventproperty.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Leanplum.track("Launch");
                Leanplum.track("View");
                Leanplum.track("Sent");
                Leanplum.track("Email Sent");
                Map<String, Object> purchaseParams = new HashMap<String, Object>();
                purchaseParams.put("Item code", 12345);
                purchaseParams.put("Name", "Coffee");
                Leanplum.trackPurchase(Leanplum.PURCHASE_EVENT_NAME, 5, "INR", purchaseParams);
            }
        });
        btninapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leanplum.track("Purchase");
                Log.v("inappppurcgase","inapp");
            }
        });
        btnpushnotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Leanplum.track("Launch");
                Leanplum.track("View");
                Log.v("notificationnn", "notification");
            }
        });
        btnappinbox.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                Leanplum.track("Sent");
                Leanplum.track("Push Sent");
            }
        });
btnappinbox.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Leanplum.getInbox();

        //Download messages. All registered InboxSyncedCallback will be invoked.
        Leanplum.getInbox().downloadMessages();

//Download messages.
//All registered InboxSyncedCallback will be invoked including the parameter callback.
        Leanplum.getInbox().downloadMessages(new InboxSyncedCallback() {
            @Override
            public void onForceContentUpdate(boolean success) {
                LeanplumInbox inbox = Leanplum.getInbox();
                //This method currently returns a list of NewsfeedMessage objects, which you'll need to cast to the new InboxMessage objects.
                List all = inbox.allMessages();
                List<LeanplumInboxMessage> messages = (List<LeanplumInboxMessage>) all;

                Log.d("leanpluminboxshow", ""+all.size());
// Do stuff with messages.
            }
        });
    }
});
    }
}