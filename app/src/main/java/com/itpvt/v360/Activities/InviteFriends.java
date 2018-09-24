package com.itpvt.v360.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.itpvt.v360.R;

public class InviteFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friends);

        sendSMS();
    }
    private void sendSMS() {
        String smsBody = "Check out V360 app for your smartphone to order Book Or see Any Photographer or Model Portfolio For Yor Event. Download it today from https://facebook.com";
        Log.i("Send SMS", "");
        Intent smsIntent = new Intent(Intent.ACTION_VIEW);

        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  , new String(""));
        smsIntent.putExtra("sms_body"  , smsBody);

        try {
            startActivity(smsIntent);
            finish();
            Log.i("Finished sending SMS...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(InviteFriends.this,"SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }
}
