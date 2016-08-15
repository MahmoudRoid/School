package ir.elegam.school.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.content.Intent;
import android.view.MenuItem;
import android.widget.TextView;

import ir.elegam.school.Adapter.ViewPagerAdapter;
import ir.elegam.school.Fragment.MessageFragment;
import ir.elegam.school.Fragment.VoiceFragment;
import ir.elegam.school.R;

public class CriticSuggestionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_critic_suggestion);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.custom_title);
        mTitle.setText("انتقادها و پیشنهادها");

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

        adapter.addFragment(new VoiceFragment(), "صدای دانش آموز");
        adapter.addFragment(new MessageFragment(), "انتقادها و پیشنهادها");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId==android.R.id.home){
            startActivity(new Intent(CriticSuggestionActivity.this,ProfileActivity.class));
        }
        return  true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CriticSuggestionActivity.this, ProfileActivity.class));
        finish();
    }
}
