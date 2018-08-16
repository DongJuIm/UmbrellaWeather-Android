package com.umbrellaweather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by user on 2017-11-27.
 */

public class RainDialog extends Activity {

 TextView resultMsg;
    String getRainMsg,getToDayRain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rain_dialog);
       // Toast.makeText(getApplicationContext(),"STAT DIALOG",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getIntent());
      getRainMsg = intent.getStringExtra("sendRainMsg");
getToDayRain =intent.getStringExtra("sendToDayRain");

        resultMsg = (TextView)findViewById(R.id.resultMsg);


     if(getRainMsg == null){


     resultMsg.setText(getToDayRain);

     }else {

         Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
         vibrator.vibrate(1000);

         PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE );
         PowerManager.WakeLock wakeLock = pm.newWakeLock( PowerManager.SCREEN_DIM_WAKE_LOCK
                 | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG" );
         wakeLock.acquire(3000);


         resultMsg.setText(getRainMsg);
     }


    }
public void onClickFinish2(View v){

    finish();

}

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }
}
