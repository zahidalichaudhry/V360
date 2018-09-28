package com.itpvt.v360.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.service.carrier.CarrierMessagingService;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.itpvt.v360.Activities.Tour.TourDashboard;
import com.itpvt.v360.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;


public class ChoosePanel extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener{

    Button register,tour;
   // LoginButton log;
  //  CallbackManager call;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 420;

    private GoogleApiClient mGoogleApiClient;

    private SignInButton btnSignIn;

    public String id;

    public LoginButton loginButton;
    public Button fb, google;
    public CallbackManager callbackManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_choose_panel);
        fb = (Button) findViewById(R.id.fb);
        google = (Button) findViewById(R.id.google);
        loginButton = (LoginButton) findViewById(R.id.login_button);


        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager= CallbackManager.Factory.create();
        register=(Button)findViewById(R.id.register);
        tour=(Button)findViewById(R.id.tour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.textactive));
        setSupportActionBar(toolbar);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChoosePanel.this,Home.class);
                startActivity(intent);
            }
        });
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChoosePanel.this,TourDashboard.class);
                startActivity(intent);
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback< LoginResult >() {@Override
        public void onSuccess(LoginResult loginResult) {

            System.out.println("onSuccess");
Intent i = new Intent(ChoosePanel.this, Home.class);
startActivity(i);
            String accessToken = loginResult.getAccessToken()
                    .getToken();
            Log.i("accessToken", accessToken);

            GraphRequest request = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback()
                    {@Override
                    public void onCompleted(JSONObject object,
                                            GraphResponse response) {

                        Log.i("LoginActivity",
                                response.toString());
                        try {
                            id = object.getString("id");
                            try {
                                URL profile_pic = new URL(
                                        "http://graph.facebook.com/" + id + "/picture?type=large");
                                Log.i("profile_pic",
                                        profile_pic + "");

                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            }
                            Log.e("UserDate", String.valueOf(object));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    });

            Bundle parameters = new Bundle();
          //  parameters.putString("fields","id,name,email,gender, birthday");
            request.setParameters(parameters);
            request.executeAsync();
        }
            @Override
            public void onCancel() {
          //      System.out.println("onCancel");
            }
            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError");
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });
        initializeControls();
        initializeGPlusSettings();
    }

    private void initializeControls(){
        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        btnSignIn.setOnClickListener(this);
    }
    private void initializeGPlusSettings(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

//    private void signOut() {
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//                        updateUI(false);
//                    }
//                });
//    }


    private void handleGPlusSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();

            Intent i = new Intent(ChoosePanel.this, Home.class);
            startActivity(i);
//            //Fetch values
//            String personName = acct.getDisplayName();
//            String personPhotoUrl = acct.getPhotoUrl().toString();
//            String email = acct.getEmail();
//            String familyName = acct.getFamilyName();
//            Log.e(TAG, "Name: " + personName +", email: " + email + ", Image: " + personPhotoUrl +", Family Name: " + familyName);
            updateUI(true);
        } else {
            updateUI(false);
        }
    }



  //




    @Override
    public void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
          //  Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleGPlusSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    handleGPlusSignInResult(googleSignInResult);
                }
            });
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent data) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleGPlusSignInResult(result);
        }else{
            super.onActivityResult(requestCode, responseCode, data);
      //      callbackManager.onActivityResult(requestCode, responseCode, data);
        }
    }

    public void onClick(View v) {
        if (v == fb) {
            loginButton.performClick();
        }else if(v == google){
            signIn();
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
        }




//        log.registerCallback(call, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//               Intent i = new Intent(ChoosePanel.this, Home.class);
//               startActivity(i);
//
//            }
//
//            @Override
//            public void onCancel() {
////                txt.setText("Login Failed");
//           }
//
//            @Override
//            public void onError(FacebookException error) {
//
//            }
//        });


    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        call.onActivityResult(requestCode,resultCode,data);
//    }

}

