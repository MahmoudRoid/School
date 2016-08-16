package ir.elegam.school.Classes;

import android.app.Application;

import com.orm.SugarApp;

public class MyApplication extends SugarApp {
    public String Token="";
    public boolean isNetworkconnected;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
