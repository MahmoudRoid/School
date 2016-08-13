package ir.elegam.school.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.elegam.school.Database.database;
import ir.elegam.school.Helper.ItemClickSupport;
import ir.elegam.school.Adapter.News_Adapter;
import ir.elegam.school.Helper.Object_News;
import ir.elegam.school.R;

public class FavoriteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Typeface San;
    private TextView txtToolbar;
    private List<Object_News> mylist = new ArrayList<>();
    private News_Adapter mAdapter;
    private RecyclerView recyclerView;
    private database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        define();
        init();

    }// end onCreate()

    private void define(){
        //db = new database(this);
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_favorite);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        recyclerView = (RecyclerView) findViewById(R.id.rv_favorite);

        txtToolbar.setTypeface(San);
        txtToolbar.setText("لیست علاقمندی ها");

        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(FavoriteActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Object_News p = mylist.get(position);
                Intent intent = new Intent(FavoriteActivity.this, ContextActivity.class);
                intent.putExtra("id", p.getOid());
                intent.putExtra("title",p.getOTile());
                intent.putExtra("matn",p.getOMatn());
                intent.putExtra("url",p.getOImageUrl());
                intent.putExtra("date",p.getODate());
                intent.putExtra("isFav", true);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            }
        });

    }// end  define()

    public void init(){
        mylist.clear();

        db.open();
        int count = db.countAll("Favorite","1");
        db.close();

        if(count>0){
            for(int i=0;i<count;i++){
                db.open();
                Object_News object_news = new Object_News(
                        db.DisplayAll(i,1,"Favorite","1"),
                        db.DisplayAll(i,2,"Favorite","1"),
                        db.DisplayAll(i,3,"Favorite","1"),
                        db.DisplayAll(i,4,"Favorite","1"),
                        db.DisplayAll(i,5,"Favorite","1"),
                        "1"
                );
                db.close();
                mylist.add(object_news);
            }
            mAdapter = new News_Adapter(mylist,San,getApplicationContext(),true);
            recyclerView.setAdapter(mAdapter);
        }
        else {
            Toast.makeText(FavoriteActivity.this, getResources().getString(R.string.error_empty_list), Toast.LENGTH_SHORT).show();
        }
    }// end init()







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
