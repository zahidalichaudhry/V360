package com.itpvt.v360;

import android.content.Context;

import com.android.volley.Request;

import java.util.ArrayList;

/**
 * Created by zahid on 05-Oct-17.
 */

public class MyCommand {
    private ArrayList<Request> requestList =new ArrayList<>();
    private Context context;
        public MyCommand(Context context)
        {
            this.context=context;
        }
        public void add(Request request)
        {
            requestList.add(request);
        }
        public void remove (Request request)
        {
             requestList.remove(request);
        }
        public  void execute()
        {
            for (Request request:requestList)
            {
                MySingleton.getInstance(context).addToRequestQueue(request);// usig the requestQue in Mysingleton To add picture to server
            }
        }
}
