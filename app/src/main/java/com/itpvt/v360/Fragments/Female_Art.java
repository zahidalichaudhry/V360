package com.itpvt.v360.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.itpvt.v360.Adapters.Recycler_Adapter_Art;
import com.itpvt.v360.Config;
import com.itpvt.v360.MySingleton2;
import com.itpvt.v360.PojoClasses.Model_album;
import com.itpvt.v360.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Female_Art extends Fragment {
    ArrayList<Model_album> arrayList=new ArrayList<>();
    RecyclerView recyclerView;
    Recycler_Adapter_Art adapter;
    RecyclerView.LayoutManager layoutManager;
    private ProgressDialog loading;


    public Female_Art() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_female__art, container, false);


        recyclerView=(RecyclerView)view.findViewById(R.id.model_recyclerView);
        layoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        loading = ProgressDialog.show(getActivity(),"Fetching...","Please wait...",false,false);
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.POST, Config.female_Art_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count=0;
                        while (count<response.length())
                        {
                            try {
                                JSONObject jsonObject=response.getJSONObject(count);
                                arrayList.add(new Model_album(jsonObject.getString("Name"),jsonObject.getString("url"),jsonObject.getString("Username")));
                                count++ ;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        adapter=new Recycler_Adapter_Art(arrayList,getActivity());
                        recyclerView.setAdapter(adapter);
                        loading.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
            }
        });
        MySingleton2.getmInstance(getActivity()).addToRequestQue(jsonArrayRequest);



        return view;
    }

}
