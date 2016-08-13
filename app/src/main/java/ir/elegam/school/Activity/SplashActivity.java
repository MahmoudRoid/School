package ir.elegam.school.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
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


        SharedPreferences prefs = getSharedPreferences("school_shared", MODE_PRIVATE);
        final boolean has_login = prefs.getBoolean("has_login",false);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(has_login){
                    startActivity(new Intent(SplashActivity.this,ProfileActivity.class));
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
                else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            }
        }, 1500);
    }

}
