package com.example.pista.tornei;

/**
 * Created by Master on 28/02/2017.
 */
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.Context;

public class MyReceiver extends BroadcastReceiver  {
    @Override
        public void onReceive(Context context, Intent intent) {
            //Intent startServiceIntent = new Intent(context, MyIntentService.class);
            //context.startService(startServiceIntent);
        PackageManager pm = context.getPackageManager();
        pm.setComponentEnabledSetting(intent.getComponent(), PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Intent mintent = new Intent(Intent.ACTION_MAIN);
        //intent.setClassName("com.example.protectedservice", "com.example.protectedservice.ActivityRestart");
        mintent.setClassName("com.example.protectedservice", "com.example.protectedservice.MainActivity");
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try {
            context.startActivity(mintent);
            //hideApplication(intent.getComponent());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
            //Util.scheduleJob(context);
        }

}
