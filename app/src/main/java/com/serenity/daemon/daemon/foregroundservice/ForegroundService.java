package com.serenity.daemon.daemon.foregroundservice;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by serenitynanian on 2018/10/24.
 * 前台服务
 */

public class ForegroundService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //startForeground让服务变成前台服务
        //两个参数的意思是：设置一个id为1的通知
        startForeground(1,new Notification());

        /**
         * 如果 18 以上的设备 启动一个service startForeground给相同的id
         *      然后结束这个服务  就不会显示通知栏了
         */
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR2){
            startService(new Intent(this, InnerSerivice.class));
        }
    }



    public static class InnerSerivice extends Service{

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            startForeground(1,new Notification());
            //结束自己
            stopSelf();
        }
    }
}
