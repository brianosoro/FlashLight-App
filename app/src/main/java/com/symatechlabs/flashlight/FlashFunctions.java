package com.symatechlabs.flashlight;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.widget.Toast;

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

public class FlashFunctions {

    public Context context;
    public static Camera camera = null;

    public FlashFunctions(Context context){
        this.context = context;
    }


    public void turnOn() {

        try {
            if (context.getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                camera = Camera.open();
                Camera.Parameters p = camera.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(p);
                camera.startPreview();
                MainActivity.state = 1;
            } else {
                Toast.makeText(context, "Seems your Device doesn't have a flash light",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {


        }
    }

    public void turnOff() {
        try {
            if (context.getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_CAMERA_FLASH)) {
                camera.stopPreview();
                camera.release();
                camera = null;
                MainActivity.state = 0;
            } else {
                Toast.makeText(context, "Seems your Device doesn't have a flash light",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {

        }
    }
}

