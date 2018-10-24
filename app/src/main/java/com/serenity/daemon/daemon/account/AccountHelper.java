package com.serenity.daemon.daemon.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by serenitynanian on 2018/10/24.
 */

public class AccountHelper {
    private static final String TAG = "AccountHelper";
    //这个account_type就是在xml文件下authenticator.xml中配置的account_type
    public static final String ACCOUNT_TYPE = "com.serenity.daemon.account";


    /**
     * 添加一个系统中存在账户类型的账户
     * @param context
     */
    public static void addAccount(Context context){
        AccountManager am = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);
        //获得此类型的账户
        Account[] accountsByType = am.getAccountsByType(ACCOUNT_TYPE);
        if (accountsByType.length > 0) {
            Log.e(TAG, "账户已存在" );
            return;
        }
        //给这个账户类型添加一个账户
        Account serenity = new Account("serenity", ACCOUNT_TYPE);
        //明确指定一个账户和密码，及所传参数
        am.addAccountExplicitly(serenity, "123", new Bundle());
    }


    /**
     * 配置同步参数----告知系统应该怎样进行同步
     * 设置账户自动同步
     *
     * 这个方法要在添加完账户后，主动调用
     */
    public static void autoAccount(){
        Account serenity = new Account("serenity", ACCOUNT_TYPE);
        //设置同步
        //第二个参数就是在sync_adapter.xml设置的contentAuthority的值
        ContentResolver.setIsSyncable(serenity,"com.serenity.daemon.provider",1);
        //设置自动同步
        ContentResolver.setSyncAutomatically(serenity,"com.serenity.daemon.provider",true);
        //设置同步周期
        ContentResolver.addPeriodicSync(serenity,"com.serenity.daemon.provider",new Bundle(),1);

        //设置立即同步，但是我们需要的是由系统主动拉活进程
//        ContentResolver.requestSync();
    }

}
