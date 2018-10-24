package com.serenity.daemon.doubledaemon;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

/**
 * Created by serenitynanian on 2018/10/24.
 */

public class MyJobService extends JobService {
    private static final String TAG = "MyJobService";

    public static void startJobService(Context context){
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(100, new ComponentName(context.getPackageName(), MyJobService.class.getName()))
                .setPersisted(true);
        //小于7.0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            //设置没隔1秒执行一次job
            //7.0以上设置无效
            builder.setPeriodic(5000);
        }else{
            //7.0以上  设置最小等待时间（时延）  延迟执行
            builder.setMinimumLatency(5000);
        }
        JobInfo jobInfo = builder.build();
        jobScheduler.schedule(jobInfo);

    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "onStartJob:-----开启jobService");
        //如果大于7.0 进行 轮循执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startJobService(this);
        }


        boolean localServiceAlive = ServiceAliveUtils.isServiceAlive(this, LocalService.class.getName());
        boolean remoteServiceAlive = ServiceAliveUtils.isServiceAlive(this, RemoteService.class.getName());
        if (!localServiceAlive || !remoteServiceAlive) {
            startService(new Intent(this, LocalService.class));
            startService(new Intent(this, RemoteService.class));
        }
        return false ;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "onStopJob:-----停止jobService");
        return false;
    }
}
