package ir.elegam.school.AsyncTask;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.Classes.Article;
import ir.elegam.school.Classes.MyApplication;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.Database.orm.db_Article;
import ir.elegam.school.Database.orm.db_KarnameTahsili;
import ir.elegam.school.Database.orm.db_NomaratAzmoonha;
import ir.elegam.school.Interface.IWebservice;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Amoozesh on 7/18/2016.
 */
public class GetArticleData extends AsyncTask<Void,Void,String> {
    public ArrayList<Article> articleArrayList;
    public Context context;
    private IWebservice delegate = null;
    private int type;  // 1= nomarat azmoonha | 2=karname | 3=  | 4 =
    SweetAlertDialog pDialog ;

    public GetArticleData(Context context, IWebservice delegate,int type){
        this.context = context;
        this.delegate = delegate;
        this.type=type;

        pDialog= new SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE);
    }

    @Override
    protected void onPreExecute() {
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("در حال دریافت اطلاعات");
        pDialog.setCancelable(false);
        pDialog.show();
    }


    @Override
    protected String doInBackground(Void... params) {
        try {
            MyApplication myApplication=(MyApplication)context.getApplicationContext();
            OkHttpClient client = new OkHttpClient();
            RequestBody body = new FormBody.Builder()
                    .add("Token",myApplication.Token)
                    .add("Code",String.valueOf(type))
                    .build();
            Request request = new Request.Builder()
                    .url(URLS.WEB_SERVICE_URL)
                    .post(body)
                    .build();
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "nothing_got";
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
        else if(!result.startsWith("[")){
            // moshkel dare kollan
            try {
                delegate.getError("problem");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            // pak kardane database ha baraye rikhtane data e jadid
            try {

                if(this.type==1){
                    List<db_NomaratAzmoonha> list = db_NomaratAzmoonha.listAll(db_NomaratAzmoonha.class);
                    if(list.size()>0){
                        db_NomaratAzmoonha.deleteAll(db_Article.class);
                    }
                }

                else if(this.type==2){
                    List<db_KarnameTahsili> list = db_KarnameTahsili.listAll(db_KarnameTahsili.class);
                    if(list.size()>0){
                        db_KarnameTahsili.deleteAll(db_Article.class);
                    }
                }

                else if(this.type==3){

                }
                else if(this.type==4){

                }

            }
            catch (Exception e){e.printStackTrace();}

            try {
                articleArrayList = new ArrayList<Article>();
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    String english_name=jsonObject.getString("english_name");
                    String semat = String.valueOf(jsonObject.getString("semat"));
                    String image_url = jsonObject.getString("url");

                    Article bankAsatid=new Article(name,english_name,semat,image_url);
                    articleArrayList.add(bankAsatid);


                    if(this.type==1){
                        db_NomaratAzmoonha db=new db_NomaratAzmoonha(name,english_name,semat,image_url);
                        db.save();
                    }

                    else if(this.type==2){
                        db_KarnameTahsili db=new db_KarnameTahsili(name,english_name,semat,image_url);
                        db.save();
                    }

                    else if(this.type==3){

                    }
                    else if(this.type==4){

                    }

                }

                if(articleArrayList.size()>0){
                    delegate.getResult(articleArrayList);
                }
                else { delegate.getError("problem");}


            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

