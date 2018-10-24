package com.serenity.daemon.daemon.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.lang.ref.WeakReference;

/**
 * Created by serenitynanian on 2018/10/24.
 */

public class KeepManager {

    private static KeepManager instance;
    private final KeepReceiver myReceiver;

    private WeakReference<KeepActivity> keepActivityWeakReference ;

    public static KeepManager getInstance(){
        if (null == instance) {
            instance = new KeepManager();
        }
        return instance ;
    }

    private KeepManager() {
        myReceiver = new KeepReceiver();
    }

    public void registerBroadcasstReceiver(Context context){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        context.registerReceiver(myReceiver, intentFilter);

    }

    public void unRegisterBroadcasstReceiver(Context context){
        if (null != myReceiver) {
            context.unregisterReceiver(myReceiver);
        }

    }

    public void startKeep(Context context){
        Intent intent = new Intent(context, KeepActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }
    public void finishKeep(Context context){
        if (null != keepActivityWeakReference) {
            KeepActivity keepActivity = keepActivityWeakReference.get();
            if (null != keepActivity) {
                keepActivity.finish();
            }
            keepActivityWeakReference = null ;
        }

    }


    public void setContext(KeepActivity keepActivity) {
        keepActivityWeakReference = new WeakReference<KeepActivity>(keepActivity);
    }
}
