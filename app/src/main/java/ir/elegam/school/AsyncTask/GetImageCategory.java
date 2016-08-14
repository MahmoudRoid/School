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
import ir.elegam.school.Classes.MyApplication;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.Database.orm.db_ImageCategoryGallery;
import ir.elegam.school.Helper.ImageCategoryGallery;
import ir.elegam.school.Interface.IWebservice;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Droid on 8/14/2016.
 */
public class GetImageCategory extends AsyncTask<String,Void,String> {
    public ArrayList<ImageCategoryGallery> imageGalleryArrayList;
    public Context context;
    private IWebservice delegate = null;

    SweetAlertDialog pDialog ;

    public GetImageCategory(Context context, IWebservice delegate){
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
                        .add("Code","Image_Category")
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

            // pak kardane database ha baraye rikhtane data e jadid
            try {
                List<db_ImageCategoryGallery> list = db_ImageCategoryGallery.listAll(db_ImageCategoryGallery.class);
                if(list.size()>0){
                    db_ImageCategoryGallery.deleteAll(db_ImageCategoryGallery.class);
                }
            }
            catch (Exception e){e.printStackTrace();}


            try {
                imageGalleryArrayList = new ArrayList<ImageCategoryGallery>();

                JSONObject jsonObject =new JSONObject(result);
                if(jsonObject.getInt("Type")==1){
                    // save Token into my application class
                    JSONArray jsonArray=jsonObject.getJSONArray("List");

                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj = jsonArray.getJSONObject(i);

                        int id=obj.getInt("Id");
                        CharSequence title=obj.getString("Title");

                        ImageCategoryGallery category=new ImageCategoryGallery(id,title);
                        imageGalleryArrayList.add(category);

                        // TODO : add too database
                        db_ImageCategoryGallery db = new db_ImageCategoryGallery(id,title);
                        db.save();
                    }

                    if(imageGalleryArrayList.size()>0){
                        delegate.getResult(imageGalleryArrayList);
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

