package ir.elegam.school.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.srx.widget.PullCallback;
import com.srx.widget.PullToLoadView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.elegam.school.AsyncTask.Async_Get_News;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.Classes.Variables;
import ir.elegam.school.Database.database;
import ir.elegam.school.Helper.ItemClickSupport;
import ir.elegam.school.Adapter.News_Adapter;
import ir.elegam.school.Helper.Object_News;
import ir.elegam.school.R;

public class NewsActivity extends AppCompatActivity implements Async_Get_News.GetNews{

    private Toolbar toolbar;
    private Typeface San;
    private TextView txtToolbar;
    private List<Object_News> mylist = new ArrayList<>();
    private String url = URLS.WEB_SERVICE_URL;
    private PullToLoadView mPullToLoadView;
    private RecyclerView mRecyclerView;
    private boolean isLoading = false;
    private boolean isHasLoadedAll = false;
    private int nextPage=1;
    private News_Adapter mAdapter;
    // private String TOKEN = Variables.TOKEN;
    private database db;
    private String FACTION = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        define();
        LoadRecycleView();

    }// end onCreate()

    private void define(){
        db = new database(this);
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_news);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);

        txtToolbar.setTypeface(San);
        txtToolbar.setText("اخبار");

        mPullToLoadView = (PullToLoadView) findViewById(R.id.pullToLoadView_news);
        mRecyclerView = mPullToLoadView.getRecyclerView();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);

        FACTION = getIntent().getStringExtra("faction");

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Object_News p = mylist.get(position);
                Intent intent = new Intent(NewsActivity.this, ContextActivity.class);
                intent.putExtra("id", p.getOid());
                intent.putExtra("title",p.getOTile());
                intent.putExtra("matn",p.getOMatn());
                intent.putExtra("url",p.getOImageUrl());
                intent.putExtra("date",p.getODate());
                intent.putExtra("isFav", false);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }// end define()

    private void LoadRecycleView(){
        mAdapter = new News_Adapter(mylist,San,this,false);
        mRecyclerView.setAdapter(mAdapter);
        mPullToLoadView.isLoadMoreEnabled(true);
        mPullToLoadView.setEnabled(false);

        mPullToLoadView.setPullCallback(new PullCallback() {
            @Override
            public void onLoadMore() {
                Log.i(Variables.Tag,"in onLoadMore()");
                SendServer();
            }// end onLoadMore()

            @Override
            public void onRefresh() {
                Log.i(Variables.Tag, "is offline");
                // if is offline
                mylist.clear();
                mAdapter.clear();
                int count = 0;
                db.open();
                count = db.countAll("Faction",FACTION);
                db.close();

                if(count>0)
                {
                    Log.i(Variables.Tag, "list is full");
                    // fill list from database
                    for(int i=0;i<count;i++){
                        db.open();
                        mylist.add(new Object_News(
                                db.DisplayAll(i,1,"Faction",FACTION),
                                db.DisplayAll(i,2,"Faction",FACTION),
                                db.DisplayAll(i,3,"Faction",FACTION),
                                db.DisplayAll(i,4,"Faction",FACTION),
                                db.DisplayAll(i,5,"Faction",FACTION),
                                db.DisplayAll(i,6,"Faction",FACTION),
                                FACTION
                        ));
                        db.close();
                    }// end for
                } else
                {
                    Log.i(Variables.Tag,"in onRefresh()");
                    nextPage=1;
                    mAdapter.clear();
                    isHasLoadedAll = false;
                    SendServer();

                }// end check database
            }

            @Override
            public boolean isLoading() {
                Log.i(Variables.Tag, "main isLoading:" + isLoading);
                return isLoading;
            }

            @Override
            public boolean hasLoadedAllItems() {
                return isHasLoadedAll;
            }

        });

        mPullToLoadView.initLoad();
    }// end LoadRecycleView()

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()

    private void SendServer(){
        if(isNetworkAvailable()){
            Async_Get_News async_get_news = new Async_Get_News();
            async_get_news.mListener = NewsActivity.this;
            async_get_news.execute(url,"TOKEN", "CODE", nextPage);
        }else{
            Toast.makeText(NewsActivity.this, getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }// end SendServer()

    @Override
    public void onFinishedRequest(String res) {
        try {
            Log.i(Variables.Tag,"res: "+res);
            Log.i(Variables.Tag,"page: "+nextPage);

            JSONObject jo = new JSONObject(res);
            JSONArray jsonArray = jo.optJSONArray("List");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String id = jsonObject.optString("Id");
                String title = jsonObject.optString("Title");
                String image_url = String.valueOf(jsonObject.optString("ImageUrl"));
                String date = jsonObject.optString("DateCreated");
                String matn = jsonObject.optString("Content");

                Object_News object_news = new Object_News(id,title,matn,date,image_url,"",FACTION);
                mAdapter.add(object_news);

                db.open();
                if(!db.CheckExistance("Sid",id)){
                    db.insert(object_news);
                }
                db.close();

            }// end for loop

            mPullToLoadView.setComplete();
            isLoading = false;

            nextPage = nextPage + 1;

        } // end try
        catch (JSONException e) {e.printStackTrace();
            Toast.makeText(NewsActivity.this, R.string.error_server, Toast.LENGTH_SHORT).show();}
        catch (Exception e) {e.printStackTrace();
            Toast.makeText(NewsActivity.this, R.string.error_server, Toast.LENGTH_SHORT).show();}

    }// end onFinishedRequest()












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
