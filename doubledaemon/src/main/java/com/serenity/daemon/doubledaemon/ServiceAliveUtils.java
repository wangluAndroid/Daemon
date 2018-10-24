package com.serenity.daemon.doubledaemon;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by serenitynanian on 2018/10/24.
 */

public class ServiceAliveUtils {

    /**
     * 判断进程是否存活
     * @param context
     * @param name
     * @return
     */
    public static boolean isServiceAlive(Context context,String name){

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = am.getRunningServices(100);
        if (null != runningServices) {
            for (ActivityManager.RunningServiceInfo runningService : runningServices) {
                if (TextUtils.equals(runningService.service.getClassName(), name)) {
                    return true;
                }
            }
        }
        return false ;
    }
}
