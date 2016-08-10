package ir.elegam.school.Classes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Amoozesh on 6/26/2016.
 */
public class Internet {

//    public boolean isonline;
//    public static Internet internet  =null;
//
//    private Internet(){}
//    public static Internet getInstance(){
//        if(internet==null){
//            internet= new Internet();
//        }
//        return internet;
//    }

//    public  void isNetworkAvailable(Context context){
//        ConnectivityManager connectivityManager
//                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()) isonline=true;
//        else isonline = false;
//    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()) return true;
        else return false;
    }
}
