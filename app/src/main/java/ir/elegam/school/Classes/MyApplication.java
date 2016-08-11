package ir.elegam.school.Classes;

import android.app.Application;

public class MyApplication extends Application {
    public String Token;
    public boolean isNetworkconnected;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
