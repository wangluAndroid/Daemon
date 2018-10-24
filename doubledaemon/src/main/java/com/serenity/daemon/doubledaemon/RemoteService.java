package com.serenity.daemon.doubledaemon;

import android.app.Notification;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by serenitynanian on 2018/10/24.
 */

public class RemoteService extends Service {


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
        startForeground(11,new Notification());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            startService(new Intent(this, InnerService.class));
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bindService(new Intent(this,LocalService.class),myServiceConnection,BIND_AUTO_CREATE);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 这个接口的作用：在绑定的LocalService 连接之后 或者 断开连接会进行回调
     *      到相应的方法
     */
    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //服务连接之后 回调
            //IBinder service  这个iBinder 就是上面RemoteService 的onBind方法中传进来的iBinder
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //LocalService断开连接回调
            startService(new Intent(RemoteService.this, LocalService.class));
            bindService(new Intent(RemoteService.this,LocalService.class),myServiceConnection,BIND_AUTO_CREATE);
        }

    }

    public static class InnerService extends Service{

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public void onCreate() {
            super.onCreate();
            startForeground(11,new Notification());
            stopSelf();
        }
    }
}
