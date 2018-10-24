package com.serenity.daemon.daemon.jobscheduler;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;

/**
 * Created by serenitynanian on 2018/10/24.
 * 使用jobService来进行拉活
 */

public class MyJobService extends JobService {

    private static final String TAG = "MyJobService";
    public static void startJobServcie(Context context){
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService(JOB_SCHEDULER_SERVICE);
//        setPersisted 用来设置  在设备重启依然执行 设置这个属性需要重启权限
//        <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
        JobInfo.Builder builder = new JobInfo.Builder(10, new ComponentName(context.getPackageName(), MyJobService.class.getName()))
                .setPersisted(true);
        //小于7.0
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            //设置没隔1秒执行一次job
            //7.0以上设置无效
            builder.setPeriodic(1000);
        }else{
            //7.0以上  设置最小等待时间（时延）  延迟执行
            builder.setMinimumLatency(1000);
        }
        JobInfo jobInfo = builder.build();
        jobScheduler.schedule(jobInfo);

    }
    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e(TAG, "onStartJob:-----开启jobService");
        //如果大于7.0 进行 轮循执行
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            startJobServcie(this);
        }
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e(TAG, "onStopJob:-----停止jobService");
        return false;
    }
}
