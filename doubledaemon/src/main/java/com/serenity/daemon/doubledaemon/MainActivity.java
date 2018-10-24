package com.serenity.daemon.doubledaemon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 双进程守护 +  JobService 提权
         */
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));
        MyJobService.startJobService(this);
    }
}
