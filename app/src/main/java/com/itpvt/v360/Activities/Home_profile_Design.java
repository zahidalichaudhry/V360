package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.itpvt.v360.Config;
import com.itpvt.v360.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class Home_profile_Design extends AppCompatActivity {
    private TextView name,username,gender,email,phone,age,address;
    InputStream is = null;
    String ID;
    ImageView m_img;
    private ProgressDialog loading;
    Button portfolio;
    String portUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        //Remove notification bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home_profile__design);
        Toolbar toolbar = (Toolbar) findViewById(R.id.intro_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        name=(TextView)findViewById(R.id.name);
        username=(TextView)findViewById(R.id.username);
        gender=(TextView)findViewById(R.id.gener);

//        email=(TextView)findViewById(R.id.email);
//        phone=(TextView)findViewById(R.id.phone);
        age=(TextView)findViewById(R.id.age);
//        address=(TextView)findViewById(R.id.address);
        m_img = (ImageView)findViewById(R.id.circleView);
        portfolio = (Button) findViewById(R.id.viewPortfolio);

        age.setVisibility(View.INVISIBLE);
        gender.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();

        ID = intent.getStringExtra("Username");
        getData();

//        m_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Home_profile_Design.this,FullScreenImage.class);
//                m_img.buildDrawingCache();
//                Bitmap bitmap = m_img.getDrawingCache();
//                Bundle extras = new Bundle();
//                extras.putParcelable("imagebitmap", bitmap);
//                intent.putExtras(extras);
//                startActivity(intent);
//            }
//        });

        portfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_profile_Design.this, ViewPortfolio_design.class);
                intent.putExtra("portUsername",portUsername);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.home_Designer_profile+ID;

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
        String saddress="";
        String surl ="";
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
            saddress=collegeData.getString(Config.KEY_ADDRESS);
            surl = collegeData.getString(Config.KEY_URL);
            portUsername = susername;
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        username.setText("Name:\t"+name+"\nAddress:\t" +email+ "\nMobile:\t"+ mobile);
        name.setText(sname);
        username.setText(susername);
//        email.setText(semail);
        gender.setText(sgender);
//        phone.setText(smobile);
        age.setText(sage);
//        address.setText(saddress);
        Glide.with(this).load(surl).into(m_img);
    }
    }


