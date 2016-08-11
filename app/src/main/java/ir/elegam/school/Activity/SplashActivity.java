package ir.elegam.school.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ir.elegam.school.Classes.Internet;
import ir.elegam.school.Classes.MyApplication;
import ir.elegam.school.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        cheknetstatus();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
            }
        }, 1500);


    }

    private void cheknetstatus() {
        MyApplication myApplication=(MyApplication)getApplicationContext();
        if(Internet.isNetworkAvailable(SplashActivity.this)){
            myApplication.isNetworkconnected=true;
        }
        else {
            myApplication.isNetworkconnected = false;
        }
    }
}
