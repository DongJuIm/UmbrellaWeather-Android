package com.umbrellaweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {

        ToDayRainTask task = new ToDayRainTask(this);
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4119060000");

        super.onStart();
    }

    public void onClickNext(View v){

    Intent intent = new Intent(MainActivity.this, InputActivity.class);
    startActivity(intent);
}
public void onClickToDayRain(View v){





    Intent intent = new Intent(getApplicationContext(),RainDialog.class);
    intent.putExtra("sendToDayRain",ToDayRainTask.todayMsg);
    startActivity(intent);

   /* if (ToDayRainTask.todayMsg!=null||ToDayRainTask.todayMsg.equals("")){

        Log.v("TODAY RAIN",ToDayRainTask.todayMsg);

        intent.putExtra("sendToDayRain",ToDayRainTask.todayMsg);
        startActivity(intent);
    }else {
        Log.v("TODAY RAIN","false");

    }
*/


}

}
