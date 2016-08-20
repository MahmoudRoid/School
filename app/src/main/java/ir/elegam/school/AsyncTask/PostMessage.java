package ir.elegam.school.AsyncTask;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.Classes.MyApplication;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.Interface.IWebservice;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Droid on 8/20/2016.
 */
public class PostMessage extends AsyncTask<Void,Void,String>{

    public Context context;
    private IWebservice delegate = null;
    public String name,phone,receiver,matn;
    SweetAlertDialog pDialog ;

    public PostMessage(Context context,IWebservice delegate,String name,String phone,String receiver,String matn){
        this.context=context;
        this.delegate=delegate;
        this.name=name;
        this.phone=phone;
        this.receiver=receiver;
        this.matn=matn;

        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
    }

    @Override
    protected void onPreExecute() {
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("در حال ارسال اطلاعات");
        pDialog.setCancelable(false);
        pDialog.show();
    }


    @Override
    protected String doInBackground(Void... voids) {
        Response response = null;
        String strResponse = "nothing_got";

        for(int i=0;i<=9;i++){
            try {
                MyApplication myApplication=(MyApplication)context.getApplicationContext();
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("Token",myApplication.Token)
                        .add("Name",this.name)
                        .add("PhoneNumeber",this.phone)
                        .add("Receiver", this.receiver)
                        .add("Matn", this.matn)
                        .build();
                Request request = new Request.Builder()
                        .url(URLS.WEB_SERVICE_URL)
                        .post(body)
                        .build();

                response = client.newCall(request).execute();
                strResponse= response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(response!=null) break;
        }
        return strResponse;
    }

    @Override
    protected void onPostExecute(String result) {
        pDialog.dismiss();

        if (result.equals("nothing_got")) {
            try {
                delegate.getError("NoData");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else {
            try {

                JSONObject jsonObject = new JSONObject(result);
                if(jsonObject.getInt("Type")==1){
                    // send shod
                    delegate.getResult("ok");
                }

                else {
                    delegate.getError("error");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
