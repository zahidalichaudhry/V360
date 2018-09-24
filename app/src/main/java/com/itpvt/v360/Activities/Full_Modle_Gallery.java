package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.itpvt.v360.Adapters.Custom_swip_adapter;
import com.itpvt.v360.Config;
import com.itpvt.v360.MySingleton2;
import com.itpvt.v360.PojoClasses.Album;
import com.itpvt.v360.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Full_Modle_Gallery extends AppCompatActivity {
    ViewPager viewPager;
    static ArrayList<Album> arrayList=new ArrayList<>();
    Custom_swip_adapter adapter;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full__modle__gallery);
        viewPager=(ViewPager)findViewById(R.id.view_pager);




        loading = ProgressDialog.show(this,"Fetching...","Please wait...",false,false);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, Config.Model_Galler, null,//this is the function through you get the name and url from jsnon arry form php file
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count=0;
                        while (count<response.length())
                        {
                            try {
                                JSONObject jsonObject=response.getJSONObject(count);
                                arrayList.add(new Album(jsonObject.getString("name"),jsonObject.getString("url")));//getthing the name and url from file and storing into the arryalist
                                count++ ;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter=new Custom_swip_adapter(Full_Modle_Gallery.this,arrayList);//now we send the name urls to adapter
                        viewPager.setAdapter(adapter);//we set that adapter to the recycerView
                        loading.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
            }
        });
        MySingleton2.getmInstance(Full_Modle_Gallery.this).addToRequestQue(jsonArrayRequest);

    }
}
