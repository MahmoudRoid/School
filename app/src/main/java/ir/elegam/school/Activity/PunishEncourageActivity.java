package ir.elegam.school.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import ir.elegam.school.Adapter.ViewPagerAdapter;
import ir.elegam.school.AsyncTask.GetPunishEncourage;
import ir.elegam.school.Classes.Internet;
import ir.elegam.school.Fragment.EncourageFragment;
import ir.elegam.school.Fragment.PunishFragment;
import ir.elegam.school.Helper.PunishEncourage;
import ir.elegam.school.Interface.IWebservice2;
import ir.elegam.school.R;

public class PunishEncourageActivity extends AppCompatActivity implements IWebservice2 {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    public ArrayList<PunishEncourage> punish_arraylist,encourage_arraylist;
    public static ArrayList<PunishEncourage> punish_arraylist_static,encourage_arraylist_static;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_punish_encourage);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.custom_title);
        mTitle.setText("تشویق و تنبیه");

        if(Internet.isNetworkAvailable(PunishEncourageActivity.this)){
            // call web service
//            GetPunishEncourage getdata= new GetPunishEncourage(PunishEncourageActivity.this,PunishEncourageActivity.this);
//            getdata.execute();
        }
        else {
            // show error  no net
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.punish_encourage_coordinator), "اتصال اینترنت خود را چک نمایید", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new PunishFragment(), "تنبیه");
        adapter.addFragment(new EncourageFragment(), "تشویق");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==android.R.id.home){
            startActivity(new Intent(PunishEncourageActivity.this,ProfileActivity.class));
        }
        return  true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(PunishEncourageActivity.this,ProfileActivity.class));
        finish();
    }




    @Override
    public void getResult(Object result, Object result2) throws Exception {
        this.punish_arraylist_static=this.punish_arraylist= (ArrayList<PunishEncourage>) result;
        this.encourage_arraylist_static=this.encourage_arraylist= (ArrayList<PunishEncourage>) result2;
    }

    @Override
    public void getError(String ErrorCodeTitle) throws Exception {
        // dade ee daryaft nashod

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.punish_encourage_coordinator), "مشکلی پیش آمده است", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static ArrayList<PunishEncourage> getPunish_arraylist_static(){
        return punish_arraylist_static;
    }

    public static ArrayList<PunishEncourage> getEncourage_arraylist_static(){
        return encourage_arraylist_static;
    }

}
