package com.itpvt.v360.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.itpvt.v360.Config;
import com.itpvt.v360.MyCommand;
import com.itpvt.v360.R;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.PhotoLoader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class UploadPortfolioArt extends AppCompatActivity {
    private static final int REQUEST_SMS = 0;
    private LinearLayout linearMain;
    Button ivGallery,ivUpload;
    GalleryPhoto galleryPhoto;
    ArrayList<String> imagelist= new ArrayList<>();
    final  int GALLERY_REQUEST=1200;
    final String TAG= this.getClass().getSimpleName();
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_upload_portfolio_art);

        linearMain=(LinearLayout)findViewById(R.id.linearMain);
        ivGallery=(Button)findViewById(R.id.ivGallery);
        ivUpload=(Button)findViewById(R.id.ivUpload);
        galleryPhoto=new GalleryPhoto(getApplicationContext());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(),R.color.textactive));
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        username = intent.getStringExtra("portusername");

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//Whole thing is to get permission from user to access the external storage

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    int hasSMSPermission = checkSelfPermission(READ_EXTERNAL_STORAGE);
                    if (hasSMSPermission != PackageManager.PERMISSION_GRANTED) {
                        if (!shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                            showMessageOKCancel("You need to allow access to Storage",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[] {READ_EXTERNAL_STORAGE},
                                                        REQUEST_SMS);
                                            }
                                        }
                                    });
                            return;
                        }
                        requestPermissions(new String[] {READ_EXTERNAL_STORAGE},
                                REQUEST_SMS);
                        return;
                    }
                    pickImage();//this the fuction after getting the permission to get Images from galery
                }
                pickImage();
            }
        });
        final MyCommand myCommand=new MyCommand(getApplicationContext());

        ivUpload.setOnClickListener(new View.OnClickListener() {//this the button fuction to upload the imgae
            @Override
            public void onClick(View view)
            {
                if(!imagelist.isEmpty()) {
                    for (String imagePath : imagelist)//we chek the image list
                    {
                        try {
                            Bitmap bitmap = PhotoLoader.init().from(imagePath).getBitmap();// initialize the bitmap
                            final String ecodedString = ImageBase64.encode(bitmap);//this is string to convert image into Imagebase64 to send to sever
                            String url = Config.UPLOAD_PROTFOLIO_ART;// path of the php file in server
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {// post method to upload the image
                                @Override
                                public void onResponse(String response) {
//                                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Error Uploading  Image", Toast.LENGTH_SHORT).show();

                                }
                            }) {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {//send image to server using params
                                    Map<String, String> prams = new HashMap<String, String>();
                                    prams.put("image", ecodedString);// we have image key word which we use in the php file and second parameter is image in ImageBase64 form
                                    prams.put("username", username);
                                    return prams;//this return the params to send image to path using php
                                }
                            };
                            myCommand.add(stringRequest);// class we make to send the images on command
//                            Toast.makeText(UploadPortfolioModel.this,"Uploaded Successfully",Toast.LENGTH_SHORT).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error Loading Image", Toast.LENGTH_SHORT).show();// exexption if there is any
                        }
                    }
                    myCommand.execute();//we execute the fuction of execute in MyCommand Class
                    Toast.makeText(UploadPortfolioArt.this,"Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UploadPortfolioArt.this, Home.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(UploadPortfolioArt.this,"No image selected", Toast.LENGTH_SHORT).show();
                }

                imagelist.remove(imagelist);
                imagelist.clear();
                linearMain.removeAllViews();
            }
        });
    }

    private void pickImage() {//fuction which pich image from gallery
        Intent in=galleryPhoto.openGalleryIntent();
        startActivityForResult(in,GALLERY_REQUEST );// open activity for result which we overwrite


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//Result Activity
        if (resultCode==RESULT_OK)
        {
            if (requestCode==GALLERY_REQUEST)
            {
                galleryPhoto.setPhotoUri(data.getData());// we added the library throug which we  get this gallery photo fuction object
                String photopath=galleryPhoto.getPath();//we get the path
                imagelist.add(photopath);//insert that to arry list
                Log.d(TAG, galleryPhoto.getPath());
                try {
                    Bitmap bitmap= PhotoLoader.init().from(photopath).getBitmap();// bitmap and load the image

                    ImageView imageView=new ImageView(getApplicationContext());// this is the image View we add to lineat layout
                    EditText editText=new EditText(getApplicationContext());
                    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    imageView.setLayoutParams(layoutParams);
                    imageView.setScaleType(ImageView.ScaleType.CENTER);
                    imageView.setPadding(10,10,10,10);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(400);
                    imageView.setImageBitmap(bitmap);
                    linearMain.addView(imageView);


                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Error Loading Image", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
    private boolean checkPermission() {// this also the fuction to get the runtime permission to get external storage
        return ( ContextCompat.checkSelfPermission(getApplicationContext(), READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE}, REQUEST_SMS);
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_SMS:
                if (grantResults.length > 0 &&  grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(), "Permission Granted, Now you can access Storage", Toast.LENGTH_SHORT).show();
                    pickImage();

                }else {
                    Toast.makeText(getApplicationContext(), "Permission Denied, You cannot access and Storage", Toast.LENGTH_SHORT).show();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                            showMessageOKCancel("You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{READ_EXTERNAL_STORAGE},
                                                        REQUEST_SMS);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(UploadPortfolioArt.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}
