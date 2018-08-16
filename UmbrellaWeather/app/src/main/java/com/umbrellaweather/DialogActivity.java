package com.umbrellaweather;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

/**
 * Created by 임동주 on 2017-11-26.
 */

public class DialogActivity extends Activity {
    CheckService mService;
   boolean mBound = false;
    String get2Utime;
    Intent b_intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_activity);

        Intent intent = new Intent(getIntent());
        get2Utime = intent.getStringExtra("sendTime");


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mBound){
            unbindService(mConnection);
            mBound = false;
        }
    }

    public void onClickFinish(View v){

        b_intent = new Intent(this,CheckService.class);
        bindService(b_intent,mConnection, Context.BIND_AUTO_CREATE);
        Log.v("onBindService","ok");

        finishAffinity();

}

private ServiceConnection mConnection = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName classname, IBinder service) {
        Log.v("onServiceConnected","ok");

        CheckService.CheckBinder binder = (CheckService.CheckBinder)service;
        mService= binder.getService();
        mBound = true;

       String a = Boolean.toString(mBound);
        Log.v("mBound State",a);


        if (mBound) {
            Log.v("onClickFinish", "in");

            b_intent.putExtra("send2Time", get2Utime);

            mService.getData(b_intent);
            mService.setRainMsg();

        }

    }


    @Override
    public void onServiceDisconnected(ComponentName classname) {
        mBound = false;
    }
};

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }






}
