package com.itpvt.v360;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyfirebaseIdservics extends FirebaseInstanceIdService
{
    private  static final String REG_TOKKEN="REQ_TOKKEN";
    @Override
    public void onTokenRefresh()
    {
        String recent_token= FirebaseInstanceId.getInstance().getToken();
        Log.d(REG_TOKKEN,recent_token);

    }
}
