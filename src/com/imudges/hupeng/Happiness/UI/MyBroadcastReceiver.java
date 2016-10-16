package com.imudges.hupeng.Happiness.UI;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by HUPENG on 2016/10/15.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean isServiceRunning = false;
        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            //检查Service状态
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
                if ("com.imudges.hupeng.Happiness.UI.LocationService".equals(service.service.getClassName())) {
                    isServiceRunning = true;
                }

            }
            if (!isServiceRunning) {
                Intent i = new Intent(context, LocationService.class);
                context.startService(i);
            }
        }
    }
}
