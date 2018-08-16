package com.umbrellaweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 임동주 on 2017-10-15.
 */

public class InputActivity extends Activity {

    EditText userTiem;
    String getUtime;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.inputdata);


    }

    public void onClickEnd(View v){

        userTiem = (EditText)findViewById(R.id.inputTime);
        getUtime = userTiem.getText().toString();

        if (getUtime.equals("")){

           Toast.makeText(getApplicationContext(),"알림 받을 시간을 입력 해주세요.",Toast.LENGTH_SHORT).show();

        }else {


            Intent intent = new Intent(getApplicationContext(),DialogActivity.class);
            intent.putExtra("sendTime",getUtime);
            Toast.makeText(getApplicationContext(),getUtime,Toast.LENGTH_SHORT).show();
            startActivity(intent);

        }


    }
}
