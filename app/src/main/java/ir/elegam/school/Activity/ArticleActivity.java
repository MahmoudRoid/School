package ir.elegam.school.Activity;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.orm.query.Select;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ir.elegam.school.Adapter.ArticleAdapter;
import ir.elegam.school.AsyncTask.GetArticleData;
import ir.elegam.school.Classes.Article;
import ir.elegam.school.Classes.Internet;
import ir.elegam.school.Classes.RecyclerItemClickListener;
import ir.elegam.school.Classes.Variables;
import ir.elegam.school.Database.orm.db_Article;
import ir.elegam.school.Database.orm.db_KarnameTahsili;
import ir.elegam.school.Database.orm.db_NomaratAzmoonha;
import ir.elegam.school.Interface.IWebservice;
import ir.elegam.school.R;

public class ArticleActivity extends AppCompatActivity implements IWebservice {

    private RecyclerView mRecyclerView;
    private ArticleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<Article> nashrieArrayList;
    public int type; // 1 = nomarat azmoonha | 2 = karname |
    private Toolbar toolbar;
    private Typeface San;
    private TextView txtToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        setArtileType();
        define();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.article_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Internet.isNetworkAvailable(ArticleActivity.this)){
                    // call webservice
                        //GetArticleData getdata=new GetArticleData(ArticleActivity.this,ArticleActivity.this,type);
                        //getdata.execute();
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.article_relative), "اتصال اینترنت خود را چک نمایید", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
        init();
    }

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_article);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setText("");
        txtToolbar.setTypeface(San);
    }// end define()

    private void setArtileType() {
        switch (getIntent().getExtras().getString("what")){
            case "نتایج آزمون":
                this.type=1;
                break;

            case "کارنامه ها":
                this.type=2;
                break;
        }
    }


    public void init(){
        //  check offline database
        ArrayList<Article> arrayList=new ArrayList<Article>();
        if(this.type==1){

            List<db_NomaratAzmoonha> list= Select.from(db_NomaratAzmoonha.class).list();
            if(list.size()>0){
                // show offline list
                for(int i=0;i<list.size();i++){
                    Article cs=new Article(list.get(i).getNumber(),list.get(i).getEnglish_name(),list.get(i).getImage_url(),list.get(i).getPdf_url());
                    arrayList.add(cs);
                }
                showList(arrayList);
            }
            else {
                // dar gheire in soorat check net va dl
                if(Internet.isNetworkAvailable(ArticleActivity.this)){
                    // call webservice
                    /*GetArticleData getdata=new GetArticleData(ArticleActivity.this,ArticleActivity.this,type);
                    getdata.execute();*/
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.article_relative), "هیچ داده ای جهت نمایش وجود ندارد", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        }


        else  if(type==2){

            List<db_KarnameTahsili> list= Select.from(db_KarnameTahsili.class).list();
            if(list.size()>0){
                // show offline list
                for(int i=0;i<list.size();i++){
                    Article cs=new Article(list.get(i).getNumber(),list.get(i).getEnglish_name(),list.get(i).getImage_url(),list.get(i).getPdf_url());
                    arrayList.add(cs);
                }
                showList(arrayList);
            }
            else {
                // dar gheire in soorat check net va dl
                if(Internet.isNetworkAvailable(ArticleActivity.this)){
                    // call webservice
                    /*GetArticleData getdata=new GetArticleData(ArticleActivity.this,ArticleActivity.this,type);
                    getdata.execute();*/
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.article_relative), "هیچ داده ای جهت نمایش وجود ندارد", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==android.R.id.home){
            startActivity(new Intent(ArticleActivity.this,ProfileActivity.class));
        }
        return  true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ArticleActivity.this,ProfileActivity.class));
        finish();
    }

    @Override
    public void getResult(Object result) throws Exception {
        showList((ArrayList<Article>) result);
    }

    @Override
    public void getError(String ErrorCodeTitle) throws Exception {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.article_relative), "مشکلی پیش آمده است . مجددا تلاش نمایید", Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public void showList(ArrayList<Article> arrayList){
        this.nashrieArrayList=arrayList;

        mRecyclerView = (RecyclerView) findViewById(R.id.article_recycler);
        mLayoutManager = new LinearLayoutManager(ArticleActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ArticleAdapter(ArticleActivity.this,nashrieArrayList);
        mRecyclerView.setAdapter(mAdapter);



        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ArticleActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // ya dl bayad beshe ya neshoon dade mishe
                file_download(nashrieArrayList.get(position).getPdf_url(),nashrieArrayList.get(position).getEnglish_name()+".pdf",position);
            }
        }));
    }

    public void file_download(String uRl,String nameoffile,int position) {

        File direct = new File(Variables.PDF);

        if (!direct.exists()) {
            direct.mkdirs();
        }

        File file = new File(direct, nameoffile);
        if (file.exists()) {
            show(nameoffile);
        }
        else {
            DownloadManager mgr = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(uRl);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false).setTitle("دانلود pdf")
                    .setDescription(nashrieArrayList.get(position).getNumber())
                    .setDestinationInExternalPublicDir("/SCHOOL/PDFs", nameoffile);
            mgr.enqueue(request);
        }
    }

    public void show(String filename){

        File file = new File(Variables.PDF +"/"+ filename);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
        }
    }

    public String gettitle() {
        String mytitle="";
        switch (mytitle=getIntent().getExtras().getString("what")){
            case "کارنامه ها":
                mytitle="کارنامه ها";
                break;
            case "نتایج آزمون":
                mytitle="نتایج آزمون";
                break;
        }
        return mytitle;
    }
}
