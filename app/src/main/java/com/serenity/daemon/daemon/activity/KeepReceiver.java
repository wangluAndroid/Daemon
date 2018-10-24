package com.serenity.daemon.daemon.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/**
 * Created by serenitynanian on 2018/10/24.
 */

public class KeepReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (TextUtils.equals(action, Intent.ACTION_SCREEN_ON)) {
            KeepManager.getInstance().finishKeep(context);

        }else if(TextUtils.equals(action, Intent.ACTION_SCREEN_OFF)){
            KeepManager.getInstance().startKeep(context);
        }
    }
}
