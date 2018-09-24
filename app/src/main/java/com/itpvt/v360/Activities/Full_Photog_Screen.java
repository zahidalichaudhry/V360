package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.itpvt.v360.Adapters.custom_photog_swip;
import com.itpvt.v360.Config;
import com.itpvt.v360.MySingleton2;
import com.itpvt.v360.PojoClasses.AlbumPort;
import com.itpvt.v360.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Full_Photog_Screen extends AppCompatActivity {
    ViewPager viewPager;
    private ProgressDialog loading;

    ArrayList<AlbumPort> arrayList=new ArrayList<>();
    custom_photog_swip adapter;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full__photog__screen);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        Intent intent=getIntent();
        userID=intent.getStringExtra("portUsername");
        String url= Config.GETTING_PHOTOG_PORTFOLIO+userID;

        loading = ProgressDialog.show(this,"Fetching...","Please wait...",false,false);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count=0;
                        while (count<response.length())
                        {
                            try {
                                JSONObject jsonObject=response.getJSONObject(count);
                                arrayList.add(new AlbumPort(jsonObject.getString("username"),jsonObject.getString("url")));
                                count++ ;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter=new custom_photog_swip(Full_Photog_Screen.this,arrayList);
                        viewPager.setAdapter(adapter);
                        loading.dismiss();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton2.getmInstance(Full_Photog_Screen.this).addToRequestQue(jsonArrayRequest);

    }
    }
