package ir.elegam.school.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import ir.elegam.school.Adapter.ImageCategoryAdapter;
import ir.elegam.school.AsyncTask.GetImageCategory;
import ir.elegam.school.Classes.Internet;
import ir.elegam.school.Classes.RecyclerItemClickListener;
import ir.elegam.school.Database.orm.db_ImageCategoryGallery;
import ir.elegam.school.Helper.ImageCategoryGallery;
import ir.elegam.school.Interface.IWebservice;
import ir.elegam.school.R;

public class ImageCategoryActivity extends AppCompatActivity implements IWebservice {

    private RecyclerView mRecyclerView;
    private ImageCategoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<ImageCategoryGallery> imageCategoryGalleryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_category);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.custom_title);
        mTitle.setText("گالری عکس");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.images_category_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Internet.isNetworkAvailable(ImageCategoryActivity.this)){
                    // call webservice
                    GetImageCategory getdata=new GetImageCategory(ImageCategoryActivity.this,ImageCategoryActivity.this);
                    getdata.execute();
                }
                else {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(R.id.images_category_relative), "اتصال اینترنت خود را چک نمایید", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });

        init();

    }

    public void init(){
        //  check offline database
        ArrayList<ImageCategoryGallery> arrayList=new ArrayList<ImageCategoryGallery>();
        List<db_ImageCategoryGallery> list= Select.from(db_ImageCategoryGallery.class).list();
        if(list.size()>0){
            // show offline list
            for(int i=0;i<list.size();i++){
                ImageCategoryGallery cs=new ImageCategoryGallery(list.get(i).getid(),list.get(i).getTitle());
                arrayList.add(cs);
            }
            showList(arrayList);
        }
        else {
            // dar gheire in soorat check net va dl

            if(Internet.isNetworkAvailable(ImageCategoryActivity.this)){
                // call webservice
                GetImageCategory getdata=new GetImageCategory(ImageCategoryActivity.this,ImageCategoryActivity.this);
                getdata.execute();
            }
            else {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.images_category_relative), "هیچ داده ای جهت نمایش وجود ندارد", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==android.R.id.home){
            startActivity(new Intent(ImageCategoryActivity.this,ProfileActivity.class));
        }
        return  true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ImageCategoryActivity.this,ProfileActivity.class));
        finish();
    }

    @Override
    public void getResult(Object result) throws Exception {
        showList((ArrayList<ImageCategoryGallery>) result);
    }

    @Override
    public void getError(String ErrorCodeTitle) throws Exception {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.images_category_relative), "مشکلی پیش آمده است . مجددا تلاش نمایید", Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public void showList(ArrayList<ImageCategoryGallery> arrayList){
        this.imageCategoryGalleryArrayList=arrayList;

        mRecyclerView = (RecyclerView) findViewById(R.id.images_category_recycler);
        mLayoutManager = new GridLayoutManager(ImageCategoryActivity.this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImageCategoryAdapter(imageCategoryGalleryArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ImageCategoryActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO : check kardane id ( k doros bashe )
                Intent intent = new Intent(ImageCategoryActivity.this,ImagesDetailActivity.class);
                intent.putExtra("id",imageCategoryGalleryArrayList.get(position).getid());
                startActivity(intent);
            }
        }));
    }
}
