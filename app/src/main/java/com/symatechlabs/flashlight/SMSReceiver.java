package com.symatechlabs.flashlight;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.telephony.SmsMessage;

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
//-----------------------------------------------------

public class SMSReceiver extends BroadcastReceiver {

    Context context;
    String msisdn, message;
    public ConnectivityManager conMgr;
    public NetworkInfo netInfo;

    FlashFunctions flashFunctions;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        flashFunctions = new FlashFunctions(context);

        final Bundle bundle = intent.getExtras();
        try {

            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (Object pdus : pdusObj) {

                    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus);
                    this.msisdn = message.getDisplayOriginatingAddress();
                    this.message = message.getDisplayMessageBody();

                    if(this.message.toLowerCase().contains("turn on")){
                        flashFunctions.turnOn();
                    }else if(this.message.toLowerCase().contains("turn off")){
                        flashFunctions.turnOff();
                    }


                }
            }


        } catch (Exception e) {

        }

    }




}
