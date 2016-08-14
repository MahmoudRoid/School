package ir.elegam.school.AsyncTask;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.Classes.MyApplication;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.Helper.PunishEncourage;
import ir.elegam.school.Interface.IWebservice2;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Droid on 8/14/2016.
 */
public class GetPunishEncourage extends AsyncTask<String,Void,String> {
    public ArrayList<PunishEncourage> punisharraylist;
    public ArrayList<PunishEncourage> encouragearraylist;
    public Context context;
    private IWebservice2 delegate = null;
    SweetAlertDialog pDialog ;

    public GetPunishEncourage(Context context, IWebservice2 delegate){
        this.context=context;
        this.delegate=delegate;
        pDialog = new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
    }

    @Override
    protected void onPreExecute() {
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("در حال دریافت اطلاعات");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        Response response = null;
        String strResponse = "nothing_got";

        for(int i=0;i<=9;i++){
            try {
                MyApplication myApplication=(MyApplication)context.getApplicationContext();
                OkHttpClient client = new OkHttpClient();
                RequestBody body = new FormBody.Builder()
                        .add("Token",myApplication.Token)
                        // todo : modify sid ( dynamic )
                        .add("Sid","0")
                        .add("Code","punish_encourage_item")
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
                punisharraylist = new ArrayList<PunishEncourage>();
                encouragearraylist = new ArrayList<PunishEncourage>();

                JSONObject jsonObject =new JSONObject(result);
                if(jsonObject.getInt("Type")==1){
                    // save Token into my application class
                    JSONArray punish_jsonArray=jsonObject.getJSONArray("Punish");
                    JSONArray encourage_jsonArray=jsonObject.getJSONArray("Encourage");

                    // parse punish
                    for(int i=0;i<punish_jsonArray.length();i++){
                        JSONObject obj = punish_jsonArray.getJSONObject(i);

                        String date=obj.getString("Date");
                        String description=obj.getString("Description");

                        PunishEncourage punish=new PunishEncourage(date,description);
                        punisharraylist.add(punish);

                        // TODO : add too database

                    }

                    // parse encourage
                    for(int i=0;i<encourage_jsonArray.length();i++){
                        JSONObject obj = encourage_jsonArray.getJSONObject(i);

                        String date=obj.getString("Date");
                        String description=obj.getString("Description");

                        PunishEncourage encourage=new PunishEncourage(date,description);
                        encouragearraylist.add(encourage);

                        // TODO : add too database

                    }



                    if((punisharraylist.size()>0) || encouragearraylist.size()>0){
                        delegate.getResult(punisharraylist,encouragearraylist);
                    }
                    else { delegate.getError("problem");}

                }
                else {
                    // set error
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

