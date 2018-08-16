package com.umbrellaweather;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by user on 2017-11-27.
 */

public class CheckService extends Service {

    private final IBinder mBinder = new CheckBinder();

    GetXMLTask task;

    String getTime;
    String  thisTime;

    int compareA;
    long mNow;

    public Date mDate=null;
    public Date u_Time;
    public Date date_thisTime;

    SimpleDateFormat tFormat = new SimpleDateFormat("HH:mm");

  public class CheckBinder extends Binder{

      CheckService getService(){
         //현재 서비스 객체 반환.
          return CheckService.this;
      }
  }
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    public void getData(Intent intent2){

        Intent getData = intent2;
        getTime = getData.getStringExtra("send2Time");

    }

 public void setRainMsg(){

     CalculatDate();

     try {

         u_Time = tFormat.parse(getTime);


     } catch (ParseException e) {
         e.printStackTrace();
     }


     compareA = date_thisTime.compareTo(u_Time);


     while (compareA !=0) {

         CalculatDate();

         compareA = date_thisTime.compareTo(u_Time);

     }

     task = new GetXMLTask(this);
     task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4119060000");

     Thread t = new Thread();
     try{
         t.sleep(1000);
     } catch (InterruptedException e) {
         e.printStackTrace();
     }

     String nowT = tFormat.format(date_thisTime);

     Handler mHandler = new Handler(getMainLooper());{
         mHandler.post(new Runnable() {

             @Override
             public void run() {
                 Intent intent=new Intent(CheckService.this,RainDialog.class);
            //     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                 intent.putExtra("sendRainMsg",GetXMLTask.msg);
                 Log.v("Send : ",GetXMLTask.msg);
                 startActivity(intent);
             }
         });

     }

 }


    public void CalculatDate() {

        mNow = System.currentTimeMillis();

        mDate = new Date(mNow);


        thisTime = tFormat.format(mDate);


        try {


            date_thisTime = tFormat.parse(thisTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
