package com.example.e_accountopening.Helper;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.provider.Telephony;
import android.telephony.gsm.SmsManager;
import android.widget.Toast;

import static android.Manifest.permission.RECEIVE_SMS;
import static android.content.Context.MODE_PRIVATE;

public class Utils {
    public static final String MY_PREFS_NAME="MY_PREFS_NAME";


    public static void savePreferences(Context context,String key,String value){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.commit();
        editor.apply();
    }

  /*  public static void savePreferences(Context context,String key,int value){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(key,value);
        editor.commit();
        editor.apply();
    }*/

    public static String getPreferences(Context context,String key){
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(key,null);
    }



    public static void deletePreferences(Context context,String key){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.remove(key);
    }
}