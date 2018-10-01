package com.itpvt.v360.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.itpvt.v360.Activities.Art_Signup;
import com.itpvt.v360.Activities.Dashboard;
import com.itpvt.v360.Activities.ForgotPassword;
import com.itpvt.v360.Activities.Model_Signup_act;
import com.itpvt.v360.Activities.Photog_signup_act;
import com.itpvt.v360.Activities.UserSignupAct;
import com.itpvt.v360.Activities.designer_signup;
import com.itpvt.v360.Config;
import com.itpvt.v360.R;
import com.itpvt.v360.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

public class Signin extends Fragment implements GoogleApiClient.OnConnectionFailedListener {



    TextView register;

    String category;
    Button signin;
    private EditText editTextEmail;
  //  LoginButton btn_facebook;
    //private SignInButton google_signin;
    private  static final int REQ_CODE= 9001;
    //private GoogleApiClient googleApiClient;
    //CallbackManager callbackManager;
    String user_email,user_nicename;
    private EditText editTextPassword;
    TextView forgot;
    String UniqueID;
    private static final String DEBUG_TAG= "V360";

    private ProgressDialog loading;

    public Signin() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  FacebookSdk.sdkInitialize(getActivity());
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_signin, container, false);
        //google_signin=(SignInButton)view.findViewById(R.id.btn_sign_in);
       //callbackManager = CallbackManager.Factory.create();

        signin = (Button) view.findViewById(R.id.btn_Login);
        editTextEmail = (EditText) view.findViewById(R.id.email);
        editTextPassword = (EditText) view.findViewById(R.id.password);
        forgot = (TextView) view.findViewById(R.id.forgot_password);

        register=(TextView) view.findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.category_signup_box, null);
                builder.setView(dialogView);


                final TextView user = (TextView) dialogView.findViewById(R.id.user);

                final TextView model = (TextView) dialogView.findViewById(R.id.model);

                final TextView photog = (TextView) dialogView.findViewById(R.id.photog);
//                final EditText Name = (EditText) dialogView.findViewById(R.id.edit2);
                final TextView design=(TextView)dialogView.findViewById(R.id.designer);

                final TextView art=(TextView)dialogView.findViewById(R.id.art);

                art.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity().getApplicationContext(), Art_Signup.class);
                        startActivity(intent);
                    }
                });


                builder.setTitle("Chose Category");
                user.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity().getApplicationContext(), UserSignupAct.class);
                        startActivity(intent);
                    }
                });
                model.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity().getApplicationContext(), Model_Signup_act.class);
                        startActivity(intent);
                    }
                });
                photog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity().getApplicationContext(), Photog_signup_act.class);
                        startActivity(intent);
                    }
                });
                design.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity().getApplicationContext(),designer_signup.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.show();




   //             Intent intent=new Intent(getActivity().getApplicationContext(), SignUp.class);
   //             startActivity(intent);
            }
        });

     //   btn_facebook = (LoginButton) view.findViewById(R.id.fb);
       // btn_facebook.setReadPermissions("public_profile email");
        //if(AccessToken.getCurrentAccessToken()!=null)
        //{
          // Intent intent = new Intent(getActivity(),Dashboard.class);
           //startActivity(intent);
       // }
       //else
       //{
      //      Log.i(DEBUG_TAG, "Not logged in to facebook.");
        //}
//
     //   btn_facebook.setFragment(this);
//
    //    btn_facebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
         //   @Override
         //  public void onSuccess(LoginResult loginResult) {
            //   if(AccessToken.getCurrentAccessToken()!=null)
           //    {
           //        RequestData();
          //          final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
          //          LayoutInflater inflater = getActivity().getLayoutInflater();
          //          final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
         //          builder.setView(dialogView);
//
//
            //       final EditText ID = (EditText) dialogView.findViewById(R.id.edit1);
//                final EditText Name = (EditText) dialogView.findViewById(R.id.edit2);
///
          //          builder.setTitle("Phone Number");
          //          builder.setMessage("Please Enter your phone Number");
         //          builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
        //               @Override
       //                public void onClick(DialogInterface dialogInterface, int i) {
      //                      else
                            {
             //          dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                     //           UniqueID = ID.getText().toString();
                      //         category="User";
             //           Username = Attributes.Name.getText().toString();
              //         arrayList.add(Username);
//                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//                                //Creating editor to store values to shared preferences
                            //    SharedPreferences.Editor editor = sharedPreferences.edit();
                             //  editor.putString(Config.PHONE_SHARED_PREF,UniqueID);
//                                editor.putString(Config.CATEGORY_SHARED_PREF,category);
////
////                                //Adding values to editor
////                                editor.putBoolean(Config.LOGGEDIN_SHARED_PREF,true);
////                                editor.putString(Config.EMAIL_SHARED_PREF, user_email);
////                                editor.putString(Config.SHARED_PREF_GOOGLE_NAME, user_nicename);
//
//                                //Saving values to editor
//                                editor.commit();
//                                registerUser();
//                                Intent intent = new Intent(getActivity(), Dashboard.class);
//                                startActivity(intent);
//                                Toast.makeText(getActivity(), "Sign in Success", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//
//                    builder.show();
//
//
//                }
//            }

//            @Override
//            public void onCancel() {
//                Toast.makeText(getActivity(), "Login Cancelled.", Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                Toast.makeText(getActivity(), "Login Error." + error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//        if(AccessToken.getCurrentAccessToken()!=null)
//        {
//            Intent intent = new Intent(getActivity(),Home.class);
//            startActivity(intent);
//        }
//        else
//        {
//            Log.i(DEBUG_TAG, "Not logged in to facebook.");
//        }

        //Loginwithfb();

       // GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        //googleApiClient = new GoogleApiClient.Builder(getActivity()).enableAutoManage(getActivity(), this).addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
//
        // initializes the spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.selection, android.R.layout.simple_spinner_item);
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
//        google_signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                signin_google();
//            }
//        });
//
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // validations
                final String user_email = editTextEmail.getText().toString().trim();

                //validations
                if (editTextEmail.getText().length()==0) {
                    editTextEmail.requestFocus();
                    editTextEmail.setError(Html.fromHtml("<font color='red'>Please Enter A Username</font>"));
                }
                else if (user_email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
                    editTextEmail.requestFocus();
                    editTextEmail.setError(Html.fromHtml("<font color='red'>Invalid Username</font>"));
                }
                else if (editTextPassword.getText().length()==0) {
                    editTextPassword.requestFocus();
                    editTextPassword.setError(Html.fromHtml("<font color='red'>Please Enter A Password</font>"));
                }
                else if(view == signin) {
                    //Calling the category function
                    checkCategory();
                }
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getActivity().getApplicationContext(), ForgotPassword.class);
                startActivity(it);
            }
        });

        return view;

    }
//    @Override
//    public void onPause(){
//        super.onPause();
////        googleApiClient.stopAutoManage(getActivity());
////        googleApiClient.disconnect();
    }
   //private void Loginwithfb()
    //{
      //  LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        //    @Override
          //public void onSuccess(LoginResult loginResult) {
            //    if(AccessToken.getCurrentAccessToken()!=null)
              //  {
                //    RequestData();
                //}

               //Intent intent = new Intent(getActivity(), Dashboard.class);
                //startActivity(intent);
                //Toast.makeText(getActivity(), "Sign in Success", Toast.LENGTH_LONG).show();
            //}
//
           //@Override
            //public void onCancel() {
              ///  Toast.makeText(getActivity(), "Login Cancelled.", Toast.LENGTH_LONG).show();
            //}
//
           //@Override
            //public void onError(FacebookException error) {
              //  Toast.makeText(getActivity(), "Login Error." + error.getMessage(), Toast.LENGTH_LONG).show();
            //}
       //});
    //}

    private void RequestData()
    {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                JSONObject json = response.getJSONObject();
                try {
                    if(json != null){
                        user_nicename = json.getString("name");
                        user_email = json.getString("email");
//                        details_txt.setText(user_nicename + user_email);

                        SharedPreferences sharedPreferences =getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                        //Creating editor to store values to shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        //Adding values to editor
                        editor.putString(Config.EMAIL_SHARED_PREF, user_email);
                        editor.putString(Config.SHARED_PREF_GOOGLE_NAME,user_nicename);

                        //Saving values to editor
                        editor.commit();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,email");
        request.setParameters(parameters);
        request.executeAsync();
    }

//    private void signin_google()
//    {
//        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
//        startActivityForResult(intent,REQ_CODE);
//    }

    //@Override
    //public void onActivityResult(int requestCode, int resultCode, Intent data) {
      //  callbackManager.onActivityResult(requestCode, resultCode, data);
        //if(requestCode==REQ_CODE)
        //{
          //  GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //handleResult(result);
       // }
    //}

   // private void handleResult(GoogleSignInResult result)
    //{
      //  if(result.isSuccess())
        //{
          //  GoogleSignInAccount account=result.getSignInAccount();
            //user_nicename = account.getDisplayName();
            //user_email = account.getEmail();
            //updateUI(true);
        //}
        //else {
          //  updateUI(false);

      //  }
    //}

//    private void updateUI(final boolean b) {
//        if (b) {
//            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            LayoutInflater inflater = getActivity().getLayoutInflater();
//            final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
//            builder.setView(dialogView);
//
//
//            final EditText ID = (EditText) dialogView.findViewById(R.id.edit1);
////                final EditText Name = (EditText) dialogView.findViewById(R.id.edit2);
//
//            builder.setTitle("Phone Number");
//            builder.setMessage("Please Enter your phone Number");
//            builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    if(ID.getText().length()==0)
//                    {
//                        ID.requestFocus();
//                        ID.setError(Html.fromHtml("<font color='red'>Please Enter Phone Number</font>"));
//                        Toast.makeText(getContext(),"Please Enter Phone Number", Toast.LENGTH_SHORT).show();
////                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
//
//                    }
//                    else
//                    {
////                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
//                        UniqueID = ID.getText().toString();
////                        Username = Name.getText().toString();
////                        arrayList.add(Username);
//                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
//
//                        //Creating editor to store values to shared preferences
//                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                        editor.putString(Config.PHONE_SHARED_PREF,UniqueID);
//
//                        //Adding values to editor
//                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF,true);
//                        editor.putString(Config.EMAIL_SHARED_PREF, user_email);
//                        editor.putString(Config.SHARED_PREF_GOOGLE_NAME, user_nicename);
//
//                        //Saving values to editor
//                        editor.commit();
//                        Intent intent = new Intent(getActivity(), Dashboard.class);
//                        startActivity(intent);
//                        Toast.makeText(getActivity(), "Sign in Success", Toast.LENGTH_LONG).show();
//                    }
//                }
//            });
           // builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             //   @Override
               // public void onClick(DialogInterface dialogInterface, int i) {
                 //   dialogInterface.dismiss();
               // }
            //});

//            builder.show();


//        } else {
  //          Toast.makeText(getActivity(), "Sign in error", Toast.LENGTH_LONG).show();
    //    }
    //}



    // check if it is user,mode or photographer
    private void checkCategory() {
        if(category.equals("Select Category"))
        {
            Toast.makeText(getActivity(),"Choose category to sign in", Toast.LENGTH_SHORT).show();
        }
       else if(category.equals("User"))
        {
            loginUser();
        }
        else if(category.equals("Model"))
        {
            loginModel();
        }
        else if(category.equals("Photographer"))
        {
            loginPhotog();
        }
        else if(category.equals("Designer"))
        {
            loginDesign();

        }
        else if(category.equals("MakeUp Artist"))
        {
            loginArt();
    }}

    private void loginArt()
    {
        //Getting values from edit texts
        final String user_email = editTextEmail.getText().toString().trim();
        final String user_pass = editTextPassword.getText().toString().trim();

        loading = ProgressDialog.show(getActivity(),"Authenticating...","Please wait...",false,false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.ART_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                            loading.dismiss();
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, user_email);
                            editor.putString(Config.CATEGORY_SHARED_PREF,category);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(getActivity().getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            loading.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_MAIL, user_email);
                params.put(Config.KEY_PASSWORD, user_pass);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    //designer Login
    private void loginDesign()
    {
        //Getting values from edit texts
        final String user_email = editTextEmail.getText().toString().trim();
        final String user_pass = editTextPassword.getText().toString().trim();

        loading = ProgressDialog.show(getActivity(),"Authenticating...","Please wait...",false,false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DESIGN_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                            loading.dismiss();
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, user_email);
                            editor.putString(Config.CATEGORY_SHARED_PREF,category);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(getActivity().getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            loading.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_MAIL, user_email);
                params.put(Config.KEY_PASSWORD, user_pass);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    // model login
    private void loginModel() {
        //Getting values from edit texts
        final String user_email = editTextEmail.getText().toString().trim();
        final String user_pass = editTextPassword.getText().toString().trim();

        loading = ProgressDialog.show(getActivity(),"Authenticating...","Please wait...",false,false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.MODEL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                            loading.dismiss();
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, user_email);
                            editor.putString(Config.CATEGORY_SHARED_PREF,category);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(getActivity().getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            loading.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_MAIL, user_email);
                params.put(Config.KEY_PASSWORD, user_pass);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
     // photographer login
    private void loginPhotog(){
        //Getting values from edit texts
        final String user_email = editTextEmail.getText().toString().trim();
        final String user_pass = editTextPassword.getText().toString().trim();

        loading = ProgressDialog.show(getActivity(),"Authenticating...","Please wait...",false,false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.PHOTOG_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                            loading.dismiss();
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, user_email);
                            editor.putString(Config.CATEGORY_SHARED_PREF,category);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(getActivity().getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            loading.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_MAIL, user_email);
                params.put(Config.KEY_PASSWORD, user_pass);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
        // user login
    private void loginUser() {
        //Getting values from edit texts
        final String user_email = editTextEmail.getText().toString().trim();
        final String user_pass = editTextPassword.getText().toString().trim();

        loading = ProgressDialog.show(getActivity(),"Authenticating...","Please wait...",false,false);

        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.USER_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.equalsIgnoreCase(Config.LOGIN_SUCCESS)) {
                            loading.dismiss();
                            //Creating a shared preference
                            SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);

                            //Creating editor to store values to shared preferences
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            //Adding values to editor
                            editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, true);
                            editor.putString(Config.EMAIL_SHARED_PREF, user_email);
                            editor.putString(Config.CATEGORY_SHARED_PREF,category);

                            //Saving values to editor
                            editor.commit();

                            //Starting profile activity
                            Intent intent = new Intent(getActivity().getApplicationContext(), Dashboard.class);
                            startActivity(intent);
                        } else {
                            //If the server response is not success
                            //Displaying an error message on toast
                            loading.dismiss();
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid username or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_MAIL, user_email);
                params.put(Config.KEY_PASSWORD, user_pass);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void registerUser() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        final String email=sharedPreferences.getString(Config.EMAIL_SHARED_PREF,null);
        final String Phone=sharedPreferences.getString(Config.PHONE_SHARED_PREF,null);

        //Fetching the boolean value form sharedpreferences
        {
            //Now will create a StringRequest almost the same as we did before

            class UpdateEmployee extends AsyncTask<Void, Void, String> {
                ProgressDialog loading;

                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(getActivity(), "User Creating...", "Please Wait...", false, false);
                }
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    if (s.equals("Successfully Register")) {
                        Intent intent=new Intent(getActivity(), Home.class);
                        startActivity(intent);
                        Toast.makeText(getActivity().getApplicationContext(), "Signup Successfully", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Sign in error! Check your network connection", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                protected String doInBackground(Void... params) {
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put(Config.SU_USER_EMAIL, email);
                    hashMap.put(Config.SU_USER_PHONE,Phone);
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
