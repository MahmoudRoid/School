package ir.elegam.school.AsyncTask;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.Classes.MyApplication;
import ir.elegam.school.Interface.IWebservice;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Amoozesh on 7/28/2016.
 */
public class PostVoice extends AsyncTask<Void,Void,String> {

    public Context context;
    private IWebservice delegate = null;
    public String base64;
    public String url;
    SweetAlertDialog pDialog ;

    public PostVoice(Context context, IWebservice delegate, String base64){
        this.context=context;
        this.delegate=delegate;
        this.base64=base64;

        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
    }

    @Override
    protected void onPreExecute() {
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("در حال ارسال صدای شما");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(Void... params) {

        Response response = null;
        String strResponse = "nothing_got";

        for(int i=0;i<=9;i++){
            try {
                MyApplication myApplication=(MyApplication)context.getApplicationContext();
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("Token",myApplication.Token)
                        .add("base64_voice",this.base64)
                        .build();
                Request request = new Request.Builder()
                        .url(this.url)
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
                JSONObject jsonObject =  new JSONObject(result);

                if(jsonObject.getInt("Type")==1){
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
