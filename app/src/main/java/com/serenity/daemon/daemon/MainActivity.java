package com.serenity.daemon.daemon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.serenity.daemon.daemon.account.AccountHelper;
import com.serenity.daemon.daemon.activity.KeepManager;
import com.serenity.daemon.daemon.foregroundservice.ForegroundService;
import com.serenity.daemon.daemon.jobscheduler.MyJobService;

import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //activity 提权
        KeepManager.getInstance().registerBroadcasstReceiver(this);

        //前台服务提权
        startService(new Intent(this, ForegroundService.class));


        //账户同步拉活
        //添加一个账户
        AccountHelper.addAccount(this);
        //告知系统  应该怎样同步
        AccountHelper.autoAccount();

        //JobService  进程拉活
        MyJobService.startJobServcie(this);

//        ByteBuffer

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unRegisterBroadcasstReceiver(this);
    }
}
