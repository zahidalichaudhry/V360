package com.itpvt.v360.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.itpvt.v360.Adapters.Recycler_adapter_model_gallery;
import com.itpvt.v360.Config;
import com.itpvt.v360.MySingleton2;
import com.itpvt.v360.PojoClasses.Album;
import com.itpvt.v360.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Model_Shoot_Gallery extends AppCompatActivity {

    private ProgressDialog loading;

    static ArrayList<Album> arrayList=new ArrayList<>();
    //static ArrayList<Album> arrayList2=new ArrayList<>();
    RecyclerView recyclerView;//initialize the recyclerView
    Recycler_adapter_model_gallery adapter;//initialize the recycleradapter to infilate
    RecyclerView.LayoutManager layoutManager;//this manage the view how you want to infilate

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model__shoot__gallery);
                recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        layoutManager=new GridLayoutManager(Model_Shoot_Gallery.this,3);
        recyclerView.setLayoutManager(layoutManager);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        loading = ProgressDialog.show(Model_Shoot_Gallery.this,"Fetching...","Please wait...",false,false);
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
                        adapter=new Recycler_adapter_model_gallery(arrayList,Model_Shoot_Gallery.this);//now we send the name urls to adapter
                        recyclerView.setAdapter(adapter);//we set that adapter to the recycerView
                        loading.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
            }
        });
        MySingleton2.getmInstance(Model_Shoot_Gallery.this).addToRequestQue(jsonArrayRequest);//using the vollay to request the jason array



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        arrayList.clear();
        adapter = null;
        recyclerView = null;
    }
}
