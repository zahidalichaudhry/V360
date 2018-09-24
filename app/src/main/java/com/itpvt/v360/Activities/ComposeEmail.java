package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.itpvt.v360.Config;
import com.itpvt.v360.R;
import com.itpvt.v360.RequestHandler;

import java.util.HashMap;

public class ComposeEmail extends AppCompatActivity {

    EditText mail_message, mail_subject;
    Button send;
    String username;
    String email;
    String phone;
    String subject;
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_compose_email);
        mail_message = (EditText) findViewById(R.id.message);
        mail_subject = (EditText) findViewById(R.id.subject);
        send = (Button) findViewById(R.id.sendEmail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        SharedPreferences prfs = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
       username = prfs.getString(Config.SHARED_PREF_USERNAME, "");
         email = prfs.getString(Config.EMAIL_SHARED_PREF, "");
        phone = prfs.getString(Config.PHONE_SHARED_PREF, "");



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterQuery();







//                String m_subject = mail_subject.getText().toString().trim();
//                String m_message = mail_message.getText().toString().trim();
//                String TO = "info@itpvt.net";
//
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.setType("message/rfc822");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, m_subject);
//                emailIntent.putExtra(Intent.EXTRA_TEXT, m_message);
//                try {
//                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//                    finish();
//                    Log.i("Finished sending email.", "");
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(ComposeEmail.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private void RegisterQuery()

    {
        subject=mail_subject.getText().toString();
        message=mail_message.getText().toString();
        {
            //Now will create a StringRequest almost the same as we did before

            class UpdateEmployee extends AsyncTask<Void, Void, String> {
                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(ComposeEmail.this, "Submitting Query...", "Please Wait...", false, false);
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    if (s.equals("Successfully Register")) {
                        Toast.makeText(ComposeEmail.this, "Query Submitted Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ComposeEmail.this, Dashboard.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ComposeEmail.this, s, Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                protected String doInBackground(Void... params) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(Config.QUERY_EMAIL, email);
                    hashMap.put(Config.QUERY_USERNAME, username);
                    hashMap.put(Config.QUERY_MOBILE, phone);
                    hashMap.put(Config.QUERY_SUBJECT, subject);
                    hashMap.put(Config.QUERY_MESSAGE, message);

                    RequestHandler rh = new RequestHandler();
                    String s = rh.sendPostRequest(Config.QUERY_SUBMITE, hashMap);
                    return s;
                }
            }
            UpdateEmployee ue = new UpdateEmployee();
            ue.execute();
        }

    }
}
