package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.HashMap;

public class ForgotPassword extends AppCompatActivity {
    String category;
    private EditText email;
    Button submit;
    String getuser;
    Button update;
    EditText pass,confrmpass;
    String password, confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText) findViewById(R.id.resetemail);
        submit = (Button) findViewById(R.id.updte);
        pass = (EditText) findViewById(R.id.pass);
        confrmpass = (EditText) findViewById(R.id.confirmpass);
        
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getemail = email.getText().toString().trim();
                // to check vaidation
                /**
                 //         * Validation
                 //
                 //         */
                boolean invalid = false;
                if (email.getText().length()==0) {
                    email.requestFocus();
                    email.setError(Html.fromHtml("<font color='red'>Please Enter Your Email</font>"));
                }
                else if (getemail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(getemail).matches()) {
                    email.requestFocus();
                    email.setError(Html.fromHtml("<font color='red'>Invalid Email Address</font>"));
                }
                if (pass.getText().length()==0) {
                    pass.requestFocus();
                    pass.setError(Html.fromHtml("<font color='red'>Please Enter Your New Password</font>"));
                }
                else if (confrmpass.getText().length()==0) {
                    confrmpass.requestFocus();
                    confrmpass.setError(Html.fromHtml("<font color='red'>Please Retype Your Password</font>"));
                }
                else
                {
                    checkPassword();
                }
            }
        });
        // initializes the spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.selection, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select gender");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // get the string of selected item
                category = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void checkPassword() {
        final String user_pass = pass.getText().toString().trim();
        final String user_cnfrmpass = confrmpass.getText().toString().trim();
        if (user_pass.equals(user_cnfrmpass)) {
            checkCategory();
        }
        else {
            confrmpass.requestFocus();
            confrmpass.setError("Password do not match");

            // Clear the password fields
            pass.setText("");
            confrmpass.setText("");
        }
    }

    private void checkCategory() {
        if(category.equals("User"))
        {
            verifyUser();
        }
        else if(category.equals("Model"))
        {
            verifyModel();
        }
        else
        {
            verifyPhotog();
        }
    }

    private void verifyPhotog() {
        getuser = email.getText().toString().trim();
        String id = getuser;

//        String url = Config.URL_VERIFY_MODEL+id;
        String url = Config.URL_FORG_VERIFY_PHOTOG+id;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSONPhotog(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgotPassword.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void verifyModel() {
        getuser = email.getText().toString().trim();
        String id = getuser;

//        String url = Config.URL_VERIFY_MODEL+id;
        String url = Config.URL_FORG_VERIFY_MODEL+id;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSONModel(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgotPassword.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void verifyUser() {
        getuser = email.getText().toString().trim();
        String id = getuser;

//        String url = Config.URL_VERIFY_MODEL+id;
        String url = Config.URL_FORG_VERIFY_USER+id;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSONUser(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgotPassword.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    // get already exist user and parse the JSON
    private void showJSONUser(String response) {
        String useremail_result = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            useremail_result = collegeData.getString(Config.VF_USER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
       if (getuser.equals(useremail_result)) {
           updateUserPassword();

        } else {
           email.requestFocus();
           email.setError("Email doesn't exist");
        }
    }

    // get already exist user and parse the JSON
    private void showJSONPhotog(String response) {
        String useremail_result = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            useremail_result = collegeData.getString(Config.VF_USER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (getuser.equals(useremail_result)) {
            updatePhotogPassword();

        } else {
            email.requestFocus();
            email.setError("Email doesn't exist");
        }
    }

    // get already exist user and parse the JSON
    private void showJSONModel(String response) {
        String useremail_result = "";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            useremail_result = collegeData.getString(Config.VF_USER);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (getuser.equals(useremail_result)) {
            updateModelPassword();

        } else {
            email.requestFocus();
            email.setError("Email doesn't exist");
        }
    }

    private void updateModelPassword() {
        final String user_nicename = email.getText().toString().trim();
        final String user_pass = pass.getText().toString().trim();

        //Now will create a StringRequest almost the same as we did before

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ForgotPassword.this, "Password Updating...", "Please Wait...", false, false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Password Reset Successfully")){
                    Toast.makeText(ForgotPassword.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPassword.this,Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ForgotPassword.this, s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.R_USER_ID, user_nicename);
                hashMap.put(Config.R_USER_PASS, user_pass);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_MODEL_CHANGE_PASS, hashMap);
                return s;

            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void updateUserPassword() {
        final String user_nicename = email.getText().toString().trim();
        final String user_pass = pass.getText().toString().trim();

        //Now will create a StringRequest almost the same as we did before

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ForgotPassword.this, "Password Updating...", "Please Wait...", false, false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Password Reset Successfully")){
                    Toast.makeText(ForgotPassword.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPassword.this,Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ForgotPassword.this, s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.R_USER_ID, user_nicename);
                hashMap.put(Config.R_USER_PASS, user_pass);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_USER_CHANGE_PASS, hashMap);
                return s;

            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }
    private void updatePhotogPassword() {
        final String user_nicename = email.getText().toString().trim();
        final String user_pass = pass.getText().toString().trim();

        //Now will create a StringRequest almost the same as we did before

        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ForgotPassword.this, "Password Updating...", "Please Wait...", false, false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equals("Password Reset Successfully")){
                    Toast.makeText(ForgotPassword.this, "Updated Successfully", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgotPassword.this,Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(ForgotPassword.this, s, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put(Config.R_USER_ID, user_nicename);
                hashMap.put(Config.R_USER_PASS, user_pass);

                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest(Config.URL_PHOTOG_CHANGE_PASS, hashMap);
                return s;

            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }
}
