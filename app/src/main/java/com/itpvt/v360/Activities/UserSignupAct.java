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

public class UserSignupAct extends AppCompatActivity {
    EditText u_name, u_username, u_email, u_phone, u_age, u_recent, u_address, u_password,u_confirmpass;
    Button u_signup;
    String user_gender;
    String getuser;
    String getusername;
    ImageView u_img;
    private final int IMG_REQUEST=1;
    private Bitmap bitmap;
    String imageUrl;
    String img_name;

    private String UploadUrl = Config.URL_CREATE_MODEL;
    private String partialurl= Config.PARTIAL_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        //Remove title bar
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        //Remove notification bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_signup);
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

            u_name = (EditText) findViewById(R.id.name);
            u_username = (EditText) findViewById(R.id.username);
            u_email = (EditText) findViewById(R.id.email);
            u_phone = (EditText) findViewById(R.id.phone);
            u_age = (EditText) findViewById(R.id.age);
        u_recent = (EditText) findViewById(R.id.recentwork);
        u_address = (EditText) findViewById(R.id.address);
        u_password = (EditText) findViewById(R.id.pass);
        u_confirmpass = (EditText) findViewById(R.id.confirmpass);
        u_signup = (Button) findViewById(R.id.u_signup);
        u_img = (ImageView) findViewById(R.id.circleView);
        u_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
        u_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to check vaidation
                final String user_email = u_email.getText().toString().trim();
                /**
                 //         * Validation
                 //
                 //         */
                boolean invalid = false;
                if (u_name.getText().length()==0) {
                    u_name.requestFocus();
                    u_name.setError(Html.fromHtml("<font color='red'>Please Enter Your Name</font>"));
                }
                else if (u_username.getText().length()==0) {
                    u_username.requestFocus();
                    u_username.setError(Html.fromHtml("<font color='red'>Please Enter Username</font>"));
                }
                else if (u_email.getText().length()==0) {
                    u_email.requestFocus();
                    u_email.setError(Html.fromHtml("<font color='red'>Please Enter Your Email</font>"));
                }
                else if (u_age.getText().length()==0) {
                    u_age.requestFocus();
                    u_age.setError(Html.fromHtml("<font color='red'>Please Enter Your Age</font>"));
                }
                else if (u_address.getText().length()==0) {
                    u_address.requestFocus();
                    u_address.setError(Html.fromHtml("<font color='red'>Please Enter Your Address</font>"));
                }
//        else if (!model_email.matches("^[a-z0-9._]{2,25}")) {
//            m_email.requestFocus();
//            m_email.setError(Html.fromHtml("<font color='red'>Invalid Email Address</font>"));
//        }
                else if (user_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
                    u_email.requestFocus();
                    u_email.setError(Html.fromHtml("<font color='red'>Invalid Email Address</font>"));
                }
                else if (u_password.getText().length()==0) {
                    u_password.requestFocus();
                    u_password.setError(Html.fromHtml("<font color='red'>Please Enter A Password</font>"));
                }
                else if (u_confirmpass.getText().length()==0) {
                    u_confirmpass.requestFocus();
                    u_confirmpass.setError(Html.fromHtml("<font color='red'>Please Retype A Password</font>"));
                }
                else if (u_phone.getText().length() < 11) {
                    invalid = true;
                    u_phone.requestFocus();
                    u_phone.setError(Html.fromHtml("<font color='red'>Please Enter Valid Mobile No</font>"));
                }
                else if (u_phone.getText().length() > 11) {
                    invalid = true;
                    u_phone.requestFocus();
                    u_phone.setError(Html.fromHtml("<font color='red'>Please Enter Valid Mobile No</font>"));
                }
                else if (bitmap == null)
                {
                    u_img.requestFocus();
                    Toast.makeText(UserSignupAct.this.getApplicationContext(),"Please Choose your picture", Toast.LENGTH_SHORT).show();
                }
                else if (view == u_signup) {

                    checkPassword();
                }
            }
        });
        // initializes the spinner
        Spinner spinner = (Spinner) findViewById(R.id.gender);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(UserSignupAct.this, R.array.gender_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select gender");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // get the string of selected item
                user_gender = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

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
                bitmap= MediaStore.Images.Media.getBitmap(UserSignupAct.this.getContentResolver(),path);//from the path store image in bitmap
                u_img.setImageBitmap(bitmap);//now show that bitmap into the image view
                u_img.setVisibility(View.VISIBLE);
            } catch (IOException e) {//if there is any execption
                e.printStackTrace();
            }
        }
    }
    private void checkPassword() {
        final String model_pass = u_password.getText().toString().trim();
        final String model_cnfrmpass = u_confirmpass.getText().toString().trim();
        if (model_pass.equals(model_cnfrmpass)) {
            checkAlreadyExist();
        }
        else {
            u_confirmpass.requestFocus();
            u_confirmpass.setError("Password do not match");

            // Clear the password fields
            u_password.setText("");
            u_confirmpass.setText("");
        }
    }
    // function to check if email is already exist
    private void checkAlreadyExist() {
        getuser = u_email.getText().toString().trim();
        getusername = u_username.getText().toString().trim();
        String id = getuser;
        String id1 = getusername;

//        String url = Config.URL_VERIFY_USER+id;
        String url = "http://itpvt.net/v360app/v360/verify_user.php?Email="+id+"&Username="+id1;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UserSignupAct.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(UserSignupAct.this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response){
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
            u_username.requestFocus();
            u_username.setError("Username already exist");
        }
        else if(getuser.equals(useremail_result))
        {
            u_email.requestFocus();
            u_email.setError("Email already exist");
        }
        else
        {
            registerUser();
        }
    }
    //this is the function to convert  the bitmap into the string
    private String imageToStrin(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgBytes=byteArrayOutputStream.toByteArray();


        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    private void registerUser() {
        final String user_nicename = u_name.getText().toString().trim();
        final String user_username = u_username.getText().toString().trim();
        final String user_email = u_email.getText().toString().trim();
        final String user_mbl = u_phone.getText().toString().trim();
        final String user_age = u_age.getText().toString().trim();
        final String user_recentwork = u_recent.getText().toString().trim();
        final String user_address = u_address.getText().toString().trim();
        final String user_pass = u_password.getText().toString().trim();
        img_name = u_username.getText().toString().trim();
        imageUrl = partialurl+img_name+".jpg";
        {
            //Now will create a StringRequest almost the same as we did before

            class UpdateEmployee extends AsyncTask<Void, Void, String> {
                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(UserSignupAct.this, "User Creating...", "Please Wait...", false, false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    if (s.equals("Successfully Register")) {
                        Intent intent=new Intent(UserSignupAct.this, Home.class);
                        startActivity(intent);
                        Toast.makeText(UserSignupAct.this.getApplicationContext(), "Signup Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(UserSignupAct.this.getApplicationContext(), "Sign in error! Check your network connection", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                protected String doInBackground(Void... params) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(Config.SU_USER_NAME, user_nicename);
                    hashMap.put(Config.SU_USER_USERNAME, user_username);
                    hashMap.put(Config.SU_USER_EMAIL, user_email);
                    hashMap.put(Config.SU_USER_PHONE, user_mbl);
                    hashMap.put(Config.SU_USER_AGE, user_age);
                    hashMap.put(Config.SU_USER_GENDER, user_gender);
                    hashMap.put(Config.SU_USER_RECENT, user_recentwork);
                    hashMap.put(Config.SU_USER_ADDRESS, user_address);
                    hashMap.put(Config.SU_USER_PASS, user_pass);
                    hashMap.put(Config.URL,imageUrl);
                    hashMap.put(Config.IMAGE,imageToStrin(bitmap));

                    RequestHandler rh = new RequestHandler();
                    String s = rh.sendPostRequest(Config.URL_CREATE_USER, hashMap);
                    return s;
                }
            }
            UpdateEmployee ue = new UpdateEmployee();
            ue.execute();
        }
    }
    }
