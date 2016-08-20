package ir.elegam.school.Activity;

import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import ir.elegam.school.Adapter.PagerAdapter_Consult;
import ir.elegam.school.Fragment.ConsultFragment;
import ir.elegam.school.Fragment.ConsultFragment1;
import ir.elegam.school.Fragment.ConsultFragment2;
import ir.elegam.school.Fragment.ConsultFragment3;
import ir.elegam.school.R;

public class ConsultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtToolbar;
    private Typeface San;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        define();

    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_consult);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setText("مشاوره تحصیلی");
        txtToolbar.setTypeface(San);

        viewPager = (ViewPager) findViewById(R.id.viewPager_consult);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs_consult);
        tabLayout.setupWithViewPager(viewPager);
        TabLayout.Tab tab = tabLayout.getTabAt(0);
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


    }// end define()

    private void setupViewPager(ViewPager viewPager) {

        PagerAdapter_Consult adapter = new PagerAdapter_Consult(getSupportFragmentManager(),this,San);

        adapter.addFragment(new ConsultFragment(), "آموزشی");
        adapter.addFragment(new ConsultFragment1(), "تربیتی");
        adapter.addFragment(new ConsultFragment2(), "کنکور");
        adapter.addFragment(new ConsultFragment3(), "اولیا");

        viewPager.setAdapter(adapter);
    }// end setupViewPager()

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
