package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itpvt.v360.Config;
import com.itpvt.v360.R;
import com.itpvt.v360.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;

public class Profile extends AppCompatActivity {
    String user_type;

//    private de.hdodenhof.circleimageview.CircleImageView ProfileImage;
    private EditText name,username,gender,email,phone,recentwork,age,address;
    InputStream is = null;
    String emailid,sendusername;
    Button update,send,porfolio;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //Remove title bar
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //Remove notification bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile);
        name=(EditText)findViewById(R.id.name);
        username=(EditText)findViewById(R.id.username);
        gender=(EditText)findViewById(R.id.gener);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        recentwork=(EditText)findViewById(R.id.recentwork);
        age=(EditText)findViewById(R.id.age);
        address=(EditText)findViewById(R.id.address);
        update=(Button)findViewById(R.id.update);
        send=(Button)findViewById(R.id.send);
        porfolio=(Button)findViewById(R.id.port);



        name.setEnabled(false);
        username.setEnabled(false);
        gender.setEnabled(false);
        email.setEnabled(false);
        phone.setEnabled(false);
        recentwork.setEnabled(false);
        age.setEnabled(false);
        address.setEnabled(false);
        porfolio.setEnabled(false);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name.setEnabled(true);
                gender.setEnabled(true);
                phone.setEnabled(true);
                recentwork.setEnabled(true);
                age.setEnabled(true);
                address.setEnabled(true);

            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (user_type != null && user_type.equals("User")) {
                    //We will start the Profile fetching process
                    UpdateUserProfile();
                }
                else if (user_type != null && user_type.equals("Model")) {
                    UpdateModelProfile();
                    Toast.makeText(Profile.this,"Update Portfolio", Toast.LENGTH_LONG);

                    Intent intent=new Intent(Profile.this,UploadPortfolioModel.class);
                    intent.putExtra("portusername", sendusername);
                    startActivity(intent);
                }
                else if (user_type != null && user_type.equals("Photographer")) {
                    UpdatePhotogProfile();
                    Toast.makeText(Profile.this,"Update Portfolio", Toast.LENGTH_LONG);
                    Intent intent=new Intent(Profile.this,UploadPortfolioPhotog.class);
                    intent.putExtra("portusername", sendusername);
                    startActivity(intent);
                }
                else if (user_type != null && user_type.equals("Designer")) {
                    UpdateDesignProfile();
                    Toast.makeText(Profile.this,"Update Portfolio", Toast.LENGTH_LONG);
                    Intent intent=new Intent(Profile.this,Upload_Designer_port.class);
                    intent.putExtra("portusername", sendusername);
                    startActivity(intent);
                }
                else if (user_type != null && user_type.equals("MakeUp Artist")) {
                    UpdateArtProfile();
                    Toast.makeText(Profile.this,"Update Portfolio", Toast.LENGTH_LONG);
                    Intent intent=new Intent(Profile.this,UploadPortfolioArt.class);
                    intent.putExtra("portusername", sendusername);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Profile.this,"User not found", Toast.LENGTH_SHORT).show();
                }

            }
        });
//        ProfileImage=(de.hdodenhof.circleimageview.CircleImageView)findViewById(R.id.imgprofile);

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Fetching the string value form sharedpreferences
        user_type = sharedPreferences.getString(Config.CATEGORY_SHARED_PREF, null);
        emailid = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, null);

        //If we will get true

        if (user_type != null && user_type.equals("User")) {
            //We will start the Profile fetching process
            getUserProfile();
            porfolio.setEnabled(false);
        }
        else if (user_type != null && user_type.equals("Model")) {
            getModelProfile();
            porfolio.setEnabled(true);
            porfolio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Profile.this,ViewPortfolio.class);
                    intent.putExtra("portUsername",sendusername);
                    startActivity(intent);
                }
            });
        }
        else if (user_type != null && user_type.equals("Photographer")) {
            getPhotogProfile();
            porfolio.setEnabled(true);
            porfolio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Profile.this,ViewPortPhotog.class);
                    intent.putExtra("portUsername", sendusername);
                    startActivity(intent);
                }
            });

        }
        else if (user_type != null && user_type.equals("Designer")) {
            getDesignProfile();
            porfolio.setEnabled(true);
            porfolio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Profile.this,ViewPortfolio_design.class);
                    intent.putExtra("portUsername", sendusername);
                    startActivity(intent);
                }
            });

        }
        else if (user_type != null && user_type.equals("MakeUp Artist")) {
            getArtProfile();
            porfolio.setEnabled(true);
            porfolio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Profile.this,View_Port_Art.class);
                    intent.putExtra("portUsername", sendusername);
                    startActivity(intent);
                }
            });

        }
        else
        {
            Toast.makeText(this,"User not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void UpdateArtProfile()
    {
        final String Email=email.getText().toString().trim();
        final String Name=name.getText().toString().trim();
        final String Gender=gender.getText().toString().trim();
        final String Phone=phone.getText().toString().trim();
        final String RecentWork=recentwork.getText().toString().trim();
        final String Age=age.getText().toString().trim();
        final String Address=address.getText().toString().trim();
        class updatedARTrofile extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Profile.this, "Data Updating...", "Please Wait...", false, false);

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Updated")){
                    Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    name.setEnabled(false);
                    gender.setEnabled(false);
                    phone.setEnabled(false);
                    recentwork.setEnabled(false);
                    age.setEnabled(false);
                    address .setEnabled(false);
                    send.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(Profile.this, s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.UPDATE_EMAIL, Email);
                hashMap.put(Config.UPDATE_NAME, Name);
                hashMap.put(Config.UPDATE_GENDER, Gender);
                hashMap.put(Config.UPDATE_PHONE, Phone);
                hashMap.put(Config.UPDATE_RECENT_WORK, RecentWork);
                hashMap.put(Config.UPDATE_AGE, Age);
                hashMap.put(Config.UPDATE_ADDRESS, Address);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.UPDATE_ART_PROFILE, hashMap);
                return s;
            }
        }
        updatedARTrofile ue = new updatedARTrofile();
        ue.execute();
    }

    private void getArtProfile()
    {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL_ART+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getDesignProfile()
    {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL_DESIGN+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void UpdateDesignProfile()
    {

        final String Email=email.getText().toString().trim();
        final String Name=name.getText().toString().trim();
        final String Gender=gender.getText().toString().trim();
        final String Phone=phone.getText().toString().trim();
        final String RecentWork=recentwork.getText().toString().trim();
        final String Age=age.getText().toString().trim();
        final String Address=address.getText().toString().trim();
        class updatedesignrofile extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Profile.this, "Data Updating...", "Please Wait...", false, false);

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Updated")){
                    Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    name.setEnabled(false);
                    gender.setEnabled(false);
                    phone.setEnabled(false);
                    recentwork.setEnabled(false);
                    age.setEnabled(false);
                    address .setEnabled(false);
                    send.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(Profile.this, s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.UPDATE_EMAIL, Email);
                hashMap.put(Config.UPDATE_NAME, Name);
                hashMap.put(Config.UPDATE_GENDER, Gender);
                hashMap.put(Config.UPDATE_PHONE, Phone);
                hashMap.put(Config.UPDATE_RECENT_WORK, RecentWork);
                hashMap.put(Config.UPDATE_AGE, Age);
                hashMap.put(Config.UPDATE_ADDRESS, Address);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.UPDATE_DESIGN_PROFILE, hashMap);
                return s;
            }
        }
        updatedesignrofile ue = new updatedesignrofile();
        ue.execute();
    }

    private void UpdateModelProfile()
    {

        final String Email=email.getText().toString().trim();
        final String Name=name.getText().toString().trim();
        final String Gender=gender.getText().toString().trim();
        final String Phone=phone.getText().toString().trim();
        final String RecentWork=recentwork.getText().toString().trim();
        final String Age=age.getText().toString().trim();
        final String Address=address.getText().toString().trim();
        class updatemodelrofile extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Profile.this, "Data Updating...", "Please Wait...", false, false);

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Updated")){
                    Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    name.setEnabled(false);
                    gender.setEnabled(false);
                    phone.setEnabled(false);
                    recentwork.setEnabled(false);
                    age.setEnabled(false);
                    address .setEnabled(false);
                    send.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(Profile.this, s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.UPDATE_EMAIL, Email);
                hashMap.put(Config.UPDATE_NAME, Name);
                hashMap.put(Config.UPDATE_GENDER, Gender);
                hashMap.put(Config.UPDATE_PHONE, Phone);
                hashMap.put(Config.UPDATE_RECENT_WORK, RecentWork);
                hashMap.put(Config.UPDATE_AGE, Age);
                hashMap.put(Config.UPDATE_ADDRESS, Address);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.UPDATE_MODEL_PROFILE, hashMap);
                return s;
            }
        }
        updatemodelrofile ue = new updatemodelrofile();
        ue.execute();
    }

    private void UpdatePhotogProfile()


    {
        final String Email=email.getText().toString().trim();
        final String Name=name.getText().toString().trim();
        final String Gender=gender.getText().toString().trim();
        final String Phone=phone.getText().toString().trim();
        final String RecentWork=recentwork.getText().toString().trim();
        final String Age=age.getText().toString().trim();
        final String Address=address.getText().toString().trim();
        class updatephotprofile extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Profile.this, "Data Updating...", "Please Wait...", false, false);

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Updated")){
                    Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    name.setEnabled(false);
                    gender.setEnabled(false);
                    phone.setEnabled(false);
                    recentwork.setEnabled(false);
                    age.setEnabled(false);
                    address .setEnabled(false);
                    send.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(Profile.this, s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.UPDATE_EMAIL, Email);
                hashMap.put(Config.UPDATE_NAME, Name);
                hashMap.put(Config.UPDATE_GENDER, Gender);
                hashMap.put(Config.UPDATE_PHONE, Phone);
                hashMap.put(Config.UPDATE_RECENT_WORK, RecentWork);
                hashMap.put(Config.UPDATE_AGE, Age);
                hashMap.put(Config.UPDATE_ADDRESS, Address);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.UPDATE_PHOTOG_PROFILE, hashMap);
                return s;
            }
        }
        updatephotprofile ue = new updatephotprofile();
        ue.execute();


    }

    private void UpdateUserProfile()

    {
        final String Email=email.getText().toString().trim();
        final String Name=name.getText().toString().trim();
        final String Gender=gender.getText().toString().trim();
        final String Phone=phone.getText().toString().trim();
        final String RecentWork=recentwork.getText().toString().trim();
        final String Age=age.getText().toString().trim();
        final String Address=address.getText().toString().trim();
        class UpdateProfile extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Profile.this, "Data Updating...", "Please Wait...", false, false);

            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Updated")){
                    Toast.makeText(Profile.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    name.setEnabled(false);
                    gender.setEnabled(false);
                    phone.setEnabled(false);
                    recentwork.setEnabled(false);
                    age.setEnabled(false);
                    address .setEnabled(false);
                    send.setVisibility(View.INVISIBLE);
                }
                else
                {
                    Toast.makeText(Profile.this, s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.UPDATE_EMAIL, Email);
                hashMap.put(Config.UPDATE_NAME, Name);
                hashMap.put(Config.UPDATE_PHONE, Phone);
                hashMap.put(Config.UPDATE_AGE, Age);
                hashMap.put(Config.UPDATE_GENDER, Gender);


                hashMap.put(Config.UPDATE_RECENT_WORK, RecentWork);

                hashMap.put(Config.UPDATE_ADDRESS, Address);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.UPDATE_USER_PROFILE, hashMap);
                return s;
            }
        }
        UpdateProfile ue = new UpdateProfile();
        ue.execute();
    }



    private void getPhotogProfile() {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL_PHOTOG+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getModelProfile() {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL_MODEL+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getUserProfile() {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.DATA_URL_USER+id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response){
        String sname="";
        String susername="";
        String semail="";
        String smobile = "";
        String sage="";
        String sgender="";
        String srecentwork="";
        String saddress="";
        String surl="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            sname = collegeData.getString(Config.KEY_NAME);
            semail = collegeData.getString(Config.KEY_EMAIL);
            smobile = collegeData.getString(Config.KEY_MOBILE);
            susername=collegeData.getString(Config.KEY_USERNAME);
            sage=collegeData.getString(Config.KEY_AGE);
            sgender=collegeData.getString(Config.KEY_GENDER);
            srecentwork=collegeData.getString(Config.KEY_RECENT_WORK);
            saddress=collegeData.getString(Config.KEY_ADDRESS);
            surl=collegeData.getString(Config.KEY_URL);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        name.setText(sname);
        username.setText(susername);
        email.setText(semail);
        gender.setText(sgender);
        phone.setText(smobile);
        age.setText(sage);
        recentwork.setText(srecentwork);
        address.setText(saddress);
        sendusername=username.getText().toString();
//        Glide.with(this).load(surl).into(ProfileImage);
    }
}
