package com.symatechlabs.flashlight;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

//======================================================================
// FlashLight App
//======================================================================
//-----------------------------------------------------
// Author   : Brian Osoro
// Company  : Symatech Labs Ltd
// Website  : www.symatechlabs.com
// Blog     : www.brianosoro.com
// Twitter  : @brayanosoro
// Email    : info@brianosoro.com / brianosoroinc@gmail.com
//----


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int PERMISSION_ALL = 1;
    Button turnon, turnoff;
    FlashFunctions flashFunctions;
    public static int state = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        askPermissions();

        flashFunctions = new FlashFunctions(MainActivity.this);


        //flashFunctions.turnOff();

        turnon = (Button) findViewById(R.id.turnon);
        turnoff = (Button) findViewById(R.id.turnoff);

        turnon.setOnClickListener(this);
        turnoff.setOnClickListener(this);

        if(state == 1){
            turnon.setVisibility(Button.GONE);
            turnoff.setVisibility(Button.VISIBLE);
        }else{
            turnon.setVisibility(Button.VISIBLE);
            turnoff.setVisibility(Button.GONE);
        }

    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    public void askPermissions() {

        String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.RECEIVE_MMS};

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.turnon:

                flashFunctions.turnOn();
                turnon.setVisibility(Button.GONE);
                turnoff.setVisibility(Button.VISIBLE);


                break;

            case R.id.turnoff:

                flashFunctions.turnOff();
                turnon.setVisibility(Button.VISIBLE);
                turnoff.setVisibility(Button.GONE);
                break;

        }

    }


}
