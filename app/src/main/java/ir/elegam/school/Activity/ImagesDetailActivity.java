package ir.elegam.school.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.elegam.school.Adapter.ImageDetailAdapter;
import ir.elegam.school.AsyncTask.GetImageCategory;
import ir.elegam.school.AsyncTask.GetImageDetail;
import ir.elegam.school.Classes.Internet;
import ir.elegam.school.Classes.RecyclerItemClickListener;
import ir.elegam.school.Database.orm.db_ImagesDetailGallery;
import ir.elegam.school.Helper.ImagesDetailGallery;
import ir.elegam.school.Interface.IWebservice;
import ir.elegam.school.R;

public class ImagesDetailActivity extends AppCompatActivity implements IWebservice {

    @BindView(R.id.images_detail_main_image)
    ImageView imagesDetailImage;
    private RecyclerView mRecyclerView;
    private ImageDetailAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public ArrayList<ImagesDetailGallery> imagesDetailGalleryArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);

        init();
    }

    public void init() {
        //  check offline database
        ArrayList<ImagesDetailGallery> arrayList = new ArrayList<ImagesDetailGallery>();
        List<db_ImagesDetailGallery> list = Select.from(db_ImagesDetailGallery.class).list();
        if (list.size() > 0) {
            // show offline list
            for (int i = 0; i < list.size(); i++) {
                ImagesDetailGallery cs = new ImagesDetailGallery(list.get(i).getid(), list.get(i).getImage_url());
                arrayList.add(cs);
            }
            showList(arrayList);
        } else {
            // dar gheire in soorat check net va dl

            if (Internet.isNetworkAvailable(ImagesDetailActivity.this)) {
                // call webservice
                GetImageDetail getdata = new GetImageDetail(ImagesDetailActivity.this, ImagesDetailActivity.this, getIntent().getExtras().getInt("id"));
                getdata.execute();
            } else {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.images_detail_relative), "هیچ داده ای جهت نمایش وجود ندارد", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            startActivity(new Intent(ImagesDetailActivity.this, ImageCategoryActivity.class));
        }
        if (itemId == R.id.custom_refresh) {
            if (Internet.isNetworkAvailable(ImagesDetailActivity.this)) {
                // call webservice
                GetImageDetail getdata = new GetImageDetail(ImagesDetailActivity.this, ImagesDetailActivity.this, getIntent().getExtras().getInt("id"));
                getdata.execute();
            } else {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.images_detail_relative), "اتصال اینترنت خود را چک نمایید", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ImagesDetailActivity.this, ImageCategoryActivity.class));
        finish();
    }

    @Override
    public void getResult(Object result) throws Exception {
        showList((ArrayList<ImagesDetailGallery>) result);
    }

    @Override
    public void getError(String ErrorCodeTitle) throws Exception {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.images_detail_relative), "مشکلی پیش آمده است . مجددا تلاش نمایید", Snackbar.LENGTH_LONG);

        snackbar.show();
    }


    public void showList(ArrayList<ImagesDetailGallery> arrayList) {
        this.imagesDetailGalleryArrayList = arrayList;
        mRecyclerView = (RecyclerView) findViewById(R.id.images_category_recycler);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new ImageDetailAdapter(ImagesDetailActivity.this, imagesDetailGalleryArrayList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ImagesDetailActivity.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Glide.with(ImagesDetailActivity.this).load(imagesDetailGalleryArrayList.get(position).getImage_url())
                        .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .placeholder(R.mipmap.ic_launcher).into(imagesDetailImage);

            }
        }));
    }

}

