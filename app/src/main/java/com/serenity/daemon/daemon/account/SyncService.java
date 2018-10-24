package com.serenity.daemon.daemon.account;

import android.accounts.Account;
import android.app.Service;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.Intent;
import android.content.SyncResult;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by serenitynanian on 2018/10/24.
 * 系统通过调用此服务  用于同步账户数据的service
 * 并且在onBind返回AbstractThreadedSyncAdapter的getSyncAdapterBinder
 *  当系统通过binder同步数据时，会调用到SyncAdapter的onPerformSync方法，执行同步
 *
 *  这个服务也是由系统调用
 */

public class SyncService extends Service {
    private static final String TAG = "SyncService";
    private SyncAdapter syncAdapter;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        syncAdapter = new SyncAdapter(this, true);
    }

    static class SyncAdapter extends AbstractThreadedSyncAdapter{

        public SyncAdapter(Context context, boolean autoInitialize) {
            super(context, autoInitialize);
        }

        @Override
        public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
            Log.e(TAG, "onPerformSync: 同步账户");
            //与互联网 或者 本地数据库同步账户
        }
    }
}
