package ir.elegam.school.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.orm.query.Select;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.Adapter.Thumbnail_Adapter;
import ir.elegam.school.AsyncTask.Async_GetVideoInfo;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.Classes.Variables;
import ir.elegam.school.Database.orm.tbl_Videos;
import ir.elegam.school.Helper.ItemClickSupport;
import ir.elegam.school.Helper.Object_Video;
import ir.elegam.school.R;


public class VideoGallaryActivity extends AppCompatActivity implements Async_GetVideoInfo.GetInfos {

    private List<Object_Video> myList = new ArrayList<>();
    private RecyclerView horizontal_recycler_view;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;
    private Toolbar toolbar;
    private Typeface San;
    private FloatingActionButton fab;
    private TextView txtToolbar;
    private Thumbnail_Adapter mAdapter;
    SweetAlertDialog pDialog ;
    final AppCompatActivity activity = null;
    private String URL = URLS.WEB_SERVICE_URL, TOKEN="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_gallary);
        define();
        init();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetVideoInfo();
            }
        });

        ItemClickSupport.addTo(horizontal_recycler_view).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Object_Video p = myList.get(position);
                String VIDEO_NAME = p.getOText();
                File file = new File(Variables.VIDEO + "/" + VIDEO_NAME + ".mp4");
                if(file.exists()){
                    Log.i(Variables.Tag,"video exists");
                    Intent intent = new Intent(VideoGallaryActivity.this,ShowVideoActivity.class);
                    intent.putExtra("url",p.getOVideo_url());
                    intent.putExtra("name",VIDEO_NAME);
                    intent.putExtra("exist",true);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }else{
                    Log.i(Variables.Tag,"video not exists");
                    Intent intent = new Intent(VideoGallaryActivity.this,ShowVideoActivity.class);
                    intent.putExtra("url",p.getOVideo_url());
                    intent.putExtra("name",VIDEO_NAME);
                    intent.putExtra("exist",false);
                    startActivity(intent);
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                }
            }
        });

    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_videogallary);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pDialog = new SweetAlertDialog(VideoGallaryActivity.this, SweetAlertDialog.PROGRESS_TYPE);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        horizontal_recycler_view = (RecyclerView) findViewById(R.id.rv_videogallary);
        fab = (FloatingActionButton) findViewById(R.id.fab_videogallary);

        txtToolbar.setText("گالری ویدئو");
        txtToolbar.setTypeface(San);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
        horizontal_recycler_view.setLayoutManager(gaggeredGridLayoutManager);

        ArcLoader();

    }// end define()

    public void init(){
        myList.clear();
        //  check offline database
        List<tbl_Videos> list= Select.from(tbl_Videos.class).list();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                myList.add(new Object_Video(
                        list.get(i).getid()+"",
                        list.get(i).getThumbnail(),
                        list.get(i).getVideo_url(),
                        list.get(i).getTitle()
                ));
                Log.i(Variables.Tag, "db id: "+ list.get(i).getid());
            }
            mAdapter = new Thumbnail_Adapter(myList,San,getApplicationContext());
            horizontal_recycler_view.setAdapter(mAdapter);
        }
        else {
            // get from server
            GetVideoInfo();

        }
    }// end init()

    private void ArcLoader(){
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("در حال دریافت اطلاعات");
        pDialog.setCancelable(false);
    }// end ArcLoader()

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()

    @Override
    public void onStratRequest() {
        pDialog.show();
    }

    @Override
    public void onFinishedRequest(String res) {
        // 1- parse and fill VinfoList
        // 2- use glide for get and show thumbnails
        pDialog.dismiss();
        Log.i(Variables.Tag,"onFinishedRequest");

        try {
            List<tbl_Videos> list = tbl_Videos.listAll(tbl_Videos.class);
            if(list.size()>0){
                tbl_Videos.deleteAll(tbl_Videos.class);
            }
            myList.clear();
        }
        catch (Exception e){e.printStackTrace();}


        try {

            Log.i(Variables.Tag,"res: "+res);

            JSONArray jsonArray = new JSONArray(res);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                int id = jsonObject.optInt("Id");
                String video_url = jsonObject.optString("url");
                String title = jsonObject.optString("Title");
                title = title.replace(' ','_');
                String content = jsonObject.optString("Content");
                String date = jsonObject.optString("datecreated");

                String ImageUrl = URLS.DOMAIN + "image/video/"+ id +".jpg";

                myList.add(new Object_Video(id+"",ImageUrl,video_url,title));

                // Save in database
                tbl_Videos tbl_videos = new tbl_Videos(id,title,ImageUrl,video_url);
                tbl_videos.save();

            }// end for loop

            mAdapter = new Thumbnail_Adapter(myList,San,this);
            horizontal_recycler_view.setAdapter(mAdapter);

        } // end try
        catch (JSONException e) {e.printStackTrace();
            Toast.makeText(VideoGallaryActivity.this, R.string.error_server, Toast.LENGTH_SHORT).show();}
        catch (Exception e) {e.printStackTrace();
            Toast.makeText(VideoGallaryActivity.this, R.string.error_server, Toast.LENGTH_SHORT).show();}

    }// end onFinishedRequest()

    private void GetVideoInfo(){

        if(isNetworkAvailable()){
            Async_GetVideoInfo async_getVideoInfo = new Async_GetVideoInfo();
            async_getVideoInfo.mListener = VideoGallaryActivity.this;
            pDialog.show();
            async_getVideoInfo.execute(URL,TOKEN,"Gallery","Video","");
        }else{
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }

    }// end GetVideoInfo()


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}// end class
