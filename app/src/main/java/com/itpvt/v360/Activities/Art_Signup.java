package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Art_Signup extends AppCompatActivity {
    EditText m_name, m_username, m_email, m_phone, m_age, m_gender, m_recent, m_address, m_password,m_confirmpass;
    Button m_signup;
    String model_gender;
    String getuser;
    String getusername;
    ImageView m_img;
    private final int IMG_REQUEST=1;
    private Bitmap bitmap;
    String imageUrl;
    ProgressDialog loading;
    String img_name;
    String portUsername;

    private String UploadUrl = Config.URL_CREATE_MODEL;
    private String partialurl= Config.PARTIAL_URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_art__signup);
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

        // initializing all the views
        m_name = (EditText) findViewById(R.id.name);
        m_username = (EditText) findViewById(R.id.username);
        m_email = (EditText)findViewById(R.id.email);
        m_phone = (EditText) findViewById(R.id.phone);
        m_age = (EditText) findViewById(R.id.age);
        m_recent = (EditText) findViewById(R.id.recentwork);
        m_address = (EditText) findViewById(R.id.address);
        m_password = (EditText) findViewById(R.id.pass);
        m_confirmpass = (EditText) findViewById(R.id.confirmpass);
        m_signup = (Button) findViewById(R.id.m_signup);
        m_img = (ImageView)findViewById(R.id.circleView);

        m_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        m_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to check vaidation
                final String model_email = m_email.getText().toString().trim();
                /**
                 //         * Validation
                 //
                 //         */
                boolean invalid = false;
                if (m_name.getText().length()==0) {
                    m_name.requestFocus();
                    m_name.setError(Html.fromHtml("<font color='red'>Please Enter Your Name</font>"));
                }
                else if (m_username.getText().length()==0) {
                    m_username.requestFocus();
                    m_username.setError(Html.fromHtml("<font color='red'>Please Enter Username</font>"));
                }
                else if (m_email.getText().length()==0) {
                    m_email.requestFocus();
                    m_email.setError(Html.fromHtml("<font color='red'>Please Enter Your Email</font>"));
                }
                else if (m_age.getText().length()==0) {
                    m_age.requestFocus();
                    m_age.setError(Html.fromHtml("<font color='red'>Please Enter Your Age</font>"));
                }
                else if (m_address.getText().length()==0) {
                    m_address.requestFocus();
                    m_address.setError(Html.fromHtml("<font color='red'>Please Enter Your Address</font>"));
                }
//        else if (!model_email.matches("^[a-z0-9._]{2,25}")) {
//            m_email.requestFocus();
//            m_email.setError(Html.fromHtml("<font color='red'>Invalid Email Address</font>"));
//        }
                else if (model_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(model_email).matches()) {
                    m_email.requestFocus();
                    m_email.setError(Html.fromHtml("<font color='red'>Invalid Email Address</font>"));
                }
                else if (m_password.getText().length()==0) {
                    m_password.requestFocus();
                    m_password.setError(Html.fromHtml("<font color='red'>Please Enter A Password</font>"));
                }
                else if (m_confirmpass.getText().length()==0) {
                    m_confirmpass.requestFocus();
                    m_confirmpass.setError(Html.fromHtml("<font color='red'>Please Retype A Password</font>"));
                }
                else if (m_phone.getText().length() < 11) {
                    invalid = true;
                    m_phone.requestFocus();
                    m_phone.setError(Html.fromHtml("<font color='red'>Please Enter Valid Mobile No</font>"));
                }
                else if (m_phone.getText().length() > 11) {
                    invalid = true;
                    m_phone.requestFocus();
                    m_phone.setError(Html.fromHtml("<font color='red'>Please Enter Valid Mobile No</font>"));
                }
                else if (bitmap == null)
                {
                    m_img.requestFocus();
                    Toast.makeText(Art_Signup.this.getApplicationContext(),"Please Choose your picture", Toast.LENGTH_SHORT).show();
                }
                else if (view == m_signup) {

                    checkPassword();
                }
            }
        });

        // initializes the spinner
        Spinner spinner = (Spinner) findViewById(R.id.gender);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(Art_Signup.this, R.array.gender_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select gender");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // get the string of selected item
                model_gender = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void selectImage() {
        Intent intent=new Intent();
        intent.setType("image/*");//this define the type of intent
        intent.setAction(Intent.ACTION_GET_CONTENT);//this define the action of the intenet
        startActivityForResult(intent,IMG_REQUEST);//this call for result when the image is selected
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==IMG_REQUEST&&resultCode == RESULT_OK &&data!=null)//chek the conditions for result
        {
            Uri path= data.getData();//get the uri path of the image
            try {
                bitmap= MediaStore.Images.Media.getBitmap(Art_Signup.this.getContentResolver(),path);//from the path store image in bitmap
                m_img.setImageBitmap(bitmap);//now show that bitmap into the image view
                m_img.setVisibility(View.VISIBLE);
            } catch (IOException e) {//if there is any execption
                e.printStackTrace();
            }
        }
    }

    private void checkPassword() {
        final String model_pass = m_password.getText().toString().trim();
        final String model_cnfrmpass = m_confirmpass.getText().toString().trim();
        if (model_pass.equals(model_cnfrmpass)) {
            checkAlreadyExist();
        }
        else {
            m_confirmpass.requestFocus();
            m_confirmpass.setError("Password do not match");

            // Clear the password fields
            m_password.setText("");
            m_confirmpass.setText("");
        }
    }

    // function to check if email is already exist
    private void checkAlreadyExist() {
        getuser = m_email.getText().toString().trim();
        getusername = m_username.getText().toString().trim();
        String id = getuser;
        String id1 = getusername;

//        String url = Config.URL_VERIFY_MODEL+id;
        String url = "http://itpvt.net/v360app/v360/varify_art.php?Email="+id+"&Username="+id1;
        loading = ProgressDialog.show(Art_Signup.this, "Checking...", "Please Wait...", false, false);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {


                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        loading.dismiss();
                        Toast.makeText(Art_Signup.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(Art_Signup.this);
        requestQueue.add(stringRequest);
    }
    // get already exist user and parse the JSON
    private void showJSON(String response)
    {           loading.dismiss();
        String useremail_result="";
        String username_result="";
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Config.JSON_ARRAY);
            JSONObject collegeData = result.getJSONObject(0);
            useremail_result = collegeData.getString(Config.VF_USER);
            username_result = collegeData.getString(Config.VF_USERNAME);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(getusername.equals(username_result))
        {
            m_username.requestFocus();
            m_username.setError("Username already exist");
        }
        else if(getuser.equals(useremail_result))
        {
            m_email.requestFocus();
            m_email.setError("Email already exist");
        }
        else
        {
            registerModel();
        }
    }
    //this is the function to convert  the bitmap into the string
    private String imageToStrin(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();


        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    private void registerModel() {
        final String model_nicename = m_name.getText().toString().trim();
        final String model_username = m_username.getText().toString().trim();
        final String model_email = m_email.getText().toString().trim();
        final String model_mbl = m_phone.getText().toString().trim();
        final String model_age = m_age.getText().toString().trim();
        final String model_recentwork = m_recent.getText().toString().trim();
        final String model_address = m_address.getText().toString().trim();
        final String model_pass = m_password.getText().toString().trim();
        img_name = m_username.getText().toString().trim();
        imageUrl = partialurl+img_name+".jpg";
        portUsername = model_username;
        {
            //Now will create a StringRequest almost the same as we did before

            class UpdateEmployee extends AsyncTask<Void, Void, String> {


                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(Art_Signup.this, "User Creating...", "Please Wait...", false, false);
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    if (s.equals("Successfully Register")) {
                        Toast.makeText(Art_Signup.this.getApplicationContext(), "Signup Successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Art_Signup.this, UploadPortfolioArt.class);
                        intent.putExtra("portusername", portUsername);
                        startActivity(intent);

                    } else {
                        Toast.makeText(Art_Signup.this.getApplicationContext(), "Sign in error! Check your network connection", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                protected String doInBackground(Void... params) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(Config.SU_MODEL_NAME, model_nicename);
                    hashMap.put(Config.SU_MODEL_USERNAME, model_username);
                    hashMap.put(Config.SU_MODEL_EMAIL, model_email);
                    hashMap.put(Config.SU_MODEL_PHONE, model_mbl);
                    hashMap.put(Config.SU_MODEL_AGE, model_age);
                    hashMap.put(Config.SU_MODEL_GENDER, model_gender);
                    hashMap.put(Config.SU_MODEL_RECENT, model_recentwork);
                    hashMap.put(Config.SU_MODEL_ADDRESS, model_address);
                    hashMap.put(Config.SU_MODEL_PASS, model_pass);
                    hashMap.put(Config.URL,imageUrl);
                    hashMap.put(Config.IMAGE,imageToStrin(bitmap));

                    RequestHandler rh = new RequestHandler();
                    String s = rh.sendPostRequest(Config.URL_CREATE_ART, hashMap);
                    return s;
                }
            }
            UpdateEmployee ue = new UpdateEmployee();
            ue.execute();
        }
    }
}
