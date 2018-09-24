package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.itpvt.v360.Adapters.DashboardPager;
import com.itpvt.v360.Config;
import com.itpvt.v360.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private de.hdodenhof.circleimageview.CircleImageView NavigationImage;
    ViewPager viewPager;
    TabLayout tabLayout;
    DashboardPager pageradapter;
    String category, Phone, emailid;
    GoogleApiClient googleApiClient;

    private ProgressDialog loading;

    private static final String DEBUG_TAG= "v360";

    private View navHeader;
    private TextView txtName, txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // change toolbar background image and title texr color
//        toolbar.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.actionbar_bg));
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.textactive));
//        toolbar.setNavigationIcon(R.drawable.lock);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API,signInOptions).build();
        googleApiClient.connect();

        //navigation drawer initialization
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.navname);
        txtEmail = (TextView) navHeader.findViewById(R.id.navemail);
        NavigationImage=(de.hdodenhof.circleimageview.CircleImageView) navHeader.findViewById(R.id.image_profile);

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tabLayout = (TabLayout)findViewById(R.id.tablayout);

        pageradapter = new DashboardPager(getSupportFragmentManager());
        viewPager.setAdapter(pageradapter);

        // initialized all the tabs
        final TabLayout.Tab home = tabLayout.newTab();
        final TabLayout.Tab gallery = tabLayout.newTab();
        final TabLayout.Tab events = tabLayout.newTab();

        // set tabs title
        home.setText("Home");
        gallery.setText("Gallery");
        events.setText("Book Now");

        // adding the tabs to tab layout
        tabLayout.addTab(home,0);
        tabLayout.addTab(gallery,1);
        tabLayout.addTab(events,2);

//         tabs text color
        tabLayout.setTabTextColors(
                ContextCompat.getColor(Dashboard.this, R.color.textinactive),
                ContextCompat.getColor(Dashboard.this, R.color.textactive)
        );

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        category = sharedPreferences.getString(Config.CATEGORY_SHARED_PREF,null);
        emailid = sharedPreferences.getString(Config.EMAIL_SHARED_PREF, null);

        getPhone();

        // Added listener when the tabs changes
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        // added on selected listener on tabs
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void getPhone() {

        //If we will get true
        if (category != null && category.equals("User")) {
            //We will start the Profile fetching process
            getUserPhone();
        }
        else if (category != null && category.equals("Model")) {
            getModelPhone();
        }
        else if (category != null && category.equals("Photog")) {
            getPhotogPhone();
        }
        else if (category != null && category.equals("Designer")) {
            getDesignerPhone();
        }
        else if (category != null && category.equals("MakeUp Artist")) {
            getArtPhone();
        }
        else
        {
          // Toast.makeText(this,"User not found",Toast.LENGTH_SHORT).show();
        }
    }

    private void getArtPhone()
    {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.PHONE_URL_ART+id;

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

    private void getDesignerPhone()
    {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.PHONE_URL_DESIGN+id;

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

    private void getPhotogPhone() {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.PHONE_URL_PHOTOG+id;

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

    private void getModelPhone() {
        String id = emailid;

        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);
        String url = Config.PHONE_URL_MODEL+id;

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

    private void getUserPhone() {
        String id = emailid;
        loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        String url = Config.PHONE_URL_USER+id;

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

    private void showJSON(String response) {
        String sname="";
        Phone = "";
        String surl="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            sname = collegeData.getString(Config.KEY_NAME);
            Phone = collegeData.getString(Config.PH_MOBILE);
            surl=collegeData.getString(Config.KEY_URL);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        SharedPreferences shared = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

        //Creating editor to store values to shared preferences
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(Config.PHONE_SHARED_PREF, Phone);
        editor.putString(Config.SHARED_PREF_USERNAME, sname);
        editor.commit();

        txtName.setText(sname);
        txtEmail.setText(emailid);
        Glide.with(Dashboard.this).load(surl).into(NavigationImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_signout) {
//            logout();
//        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");
                        editor.putString(Config.CATEGORY_SHARED_PREF, "");
                        editor.putString(Config.PHONE_SHARED_PREF, "");

                        //Saving the sharedpreferences
                        editor.clear();
                        editor.apply();

                        facebookLogout();
                        googleLogout();
                        //Starting login activity
                        Intent intent = new Intent(Dashboard.this, Home.class);
                        startActivity(intent);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    private void facebookLogout() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        LoginManager.getInstance().logOut();
        AccessToken.setCurrentAccessToken(null);
    }
    private void googleLogout() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Log.i(DEBUG_TAG, "Log out from google.");
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            Intent i = new Intent(this,Profile.class);
            startActivity(i);
            // Handle the camera action
        }
        else if (id == R.id.nav_chngepass)
        {
            Intent it = new Intent(this, ForgotPassword.class);
            startActivity(it);
        }
        else if (id == R.id.nav_home)
        {
            Intent it = new Intent(this, Dashboard.class);
            startActivity(it);
        }
        else if (id == R.id.nav_inivite)
        {
            Intent it = new Intent(this, InviteFriends.class);
            startActivity(it);
        }
        else if (id == R.id.nav_logout)
        {
            logout();
        }
        else if (id == R.id.nav_contact)
        {
            Intent it = new Intent(this, ContactUs.class);
            startActivity(it);
        }
        else if (id == R.id.nav_about)
        {
            Intent it = new Intent(this, AboutUs.class);
            startActivity(it);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
