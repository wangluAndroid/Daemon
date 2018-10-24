package com.serenity.daemon.doubledaemon;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by serenitynanian on 2018/10/24.
 *
 *  实现双进程守护
 *
 *   先将服务调用startForeground 变成前台服务  提高进程优先级
 */

public class LocalService extends Service {

    private MyBinder myBinder;
    private MyServiceConnection myServiceConnection;

    static class MyBinder extends IMyAidlInterface.Stub{
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myBinder = new MyBinder();
        myServiceConnection = new MyServiceConnection();
        startForeground(10,new Notification());
        // 18 以上给一个相同id的Notification 然后再结束这个服务 通知栏就不会显示这个通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            startService(new Intent(this, InnerService.class));
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    class MyServiceConnection implements ServiceConnection{
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            /**
             * 这里为什么要startService之后还要bindService?
             *      如果只掉用bindService，用户unBindService之后 服务就不能自动启动，所以两用两次
             */
            startService(new Intent(LocalService.this, RemoteService.class));
            bindService(new Intent(LocalService.this, RemoteService.class), myServiceConnection, BIND_AUTO_CREATE);
        }
    }

    public static class InnerService extends Service {
        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            startForeground(10,new Notification());
            stopSelf();
        }
    }



}
