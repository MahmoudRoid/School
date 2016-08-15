package ir.elegam.school.Activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.AsyncTask.Async_GetSchedual;
import ir.elegam.school.AsyncTask.Async_Get_News;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.R;

public class WeekActivity extends AppCompatActivity implements Async_GetSchedual.GetSchedual{

    private Typeface San;
    private TextView txtToolbar;
    private Toolbar toolbar;
    private float mx, my;
    private ScrollView vScroll;
    private HorizontalScrollView hScroll;
    private SweetAlertDialog pDialog;
    private String URL = URLS.WEB_SERVICE_URL;
    private TextView txtTitle1,txtTitle2,txtTitle3,txtTitle4,txtTitle5,txtTitle6,txtTitle;
    private TextView txtShanbe,txt1Shanbe,txt2Shanbe,txt3Shanbe,txt4Shanbe,txt5Shanbe,txtJome;
    private TextView txtClock10,txtClock20,txtClock30,txtClock40,txtClock50,txtClock60;
    private TextView txtClock11,txtClock21,txtClock31,txtClock41,txtClock51,txtClock61;
    private TextView txtClock12,txtClock22,txtClock32,txtClock42,txtClock52,txtClock62;
    private TextView txtClock13,txtClock23,txtClock33,txtClock43,txtClock53,txtClock63;
    private TextView txtClock14,txtClock24,txtClock34,txtClock44,txtClock54,txtClock64;
    private TextView txtClock15,txtClock25,txtClock35,txtClock45,txtClock55,txtClock65;
    private TextView txtClock16,txtClock26,txtClock36,txtClock46,txtClock56,txtClock66;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);
        define();


    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_week);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        vScroll = (ScrollView) findViewById(R.id.vScroll_week);
        hScroll = (HorizontalScrollView) findViewById(R.id.hScroll_week);

        txtTitle = (TextView) findViewById(R.id.txtTitleHeader_week);
        txtTitle1 = (TextView) findViewById(R.id.txt1Header_week);
        txtTitle2 = (TextView) findViewById(R.id.txt2Header_week);
        txtTitle3 = (TextView) findViewById(R.id.txt3Header_week);
        txtTitle4 = (TextView) findViewById(R.id.txt4Header_week);
        txtTitle5 = (TextView) findViewById(R.id.txt5Header_week);
        txtTitle6 = (TextView) findViewById(R.id.txt6Header_week);

        txtShanbe = (TextView) findViewById(R.id.txtTitle1_week);
        txt1Shanbe = (TextView) findViewById(R.id.txtTitle2_week);
        txt2Shanbe = (TextView) findViewById(R.id.txtTitle3_week);
        txt3Shanbe = (TextView) findViewById(R.id.txtTitle4_week);
        txt4Shanbe = (TextView) findViewById(R.id.txtTitle5_week);
        txt5Shanbe = (TextView) findViewById(R.id.txtTitle6_week);
        txtJome = (TextView) findViewById(R.id.txtTitle7_week);

        txtClock10 = (TextView) findViewById(R.id.txt11_week);
        txtClock20 = (TextView) findViewById(R.id.txt21_week);
        txtClock30 = (TextView) findViewById(R.id.txt31_week);
        txtClock40 = (TextView) findViewById(R.id.txt41_week);
        txtClock50 = (TextView) findViewById(R.id.txt51_week);
        txtClock60 = (TextView) findViewById(R.id.txt61_week);

        txtClock11 = (TextView) findViewById(R.id.txt12_week);
        txtClock21 = (TextView) findViewById(R.id.txt22_week);
        txtClock31 = (TextView) findViewById(R.id.txt32_week);
        txtClock41 = (TextView) findViewById(R.id.txt42_week);
        txtClock51 = (TextView) findViewById(R.id.txt52_week);
        txtClock61 = (TextView) findViewById(R.id.txt62_week);

        txtClock12 = (TextView) findViewById(R.id.txt13_week);
        txtClock22 = (TextView) findViewById(R.id.txt23_week);
        txtClock32 = (TextView) findViewById(R.id.txt33_week);
        txtClock42 = (TextView) findViewById(R.id.txt43_week);
        txtClock52 = (TextView) findViewById(R.id.txt53_week);
        txtClock62 = (TextView) findViewById(R.id.txt63_week);

        txtClock13 = (TextView) findViewById(R.id.txt14_week);
        txtClock23 = (TextView) findViewById(R.id.txt24_week);
        txtClock33 = (TextView) findViewById(R.id.txt34_week);
        txtClock43 = (TextView) findViewById(R.id.txt44_week);
        txtClock53 = (TextView) findViewById(R.id.txt54_week);
        txtClock63 = (TextView) findViewById(R.id.txt64_week);

        txtClock14 = (TextView) findViewById(R.id.txt15_week);
        txtClock24 = (TextView) findViewById(R.id.txt25_week);
        txtClock34 = (TextView) findViewById(R.id.txt35_week);
        txtClock44 = (TextView) findViewById(R.id.txt45_week);
        txtClock54 = (TextView) findViewById(R.id.txt55_week);
        txtClock64 = (TextView) findViewById(R.id.txt65_week);

        txtClock15 = (TextView) findViewById(R.id.txt16_week);
        txtClock25 = (TextView) findViewById(R.id.txt26_week);
        txtClock35 = (TextView) findViewById(R.id.txt36_week);
        txtClock45 = (TextView) findViewById(R.id.txt46_week);
        txtClock55 = (TextView) findViewById(R.id.txt56_week);
        txtClock65 = (TextView) findViewById(R.id.txt66_week);

        txtClock16 = (TextView) findViewById(R.id.txt17_week);
        txtClock26 = (TextView) findViewById(R.id.txt27_week);
        txtClock36 = (TextView) findViewById(R.id.txt37_week);
        txtClock46 = (TextView) findViewById(R.id.txt47_week);
        txtClock56 = (TextView) findViewById(R.id.txt57_week);
        txtClock66 = (TextView) findViewById(R.id.txt67_week);

        txtTitle1.setTypeface(San);
        txtTitle2.setTypeface(San);
        txtTitle3.setTypeface(San);
        txtTitle4.setTypeface(San);
        txtTitle5.setTypeface(San);
        txtTitle6.setTypeface(San);
        txtTitle.setTypeface(San);
        txtShanbe.setTypeface(San);
        txt1Shanbe.setTypeface(San);
        txt2Shanbe.setTypeface(San);
        txt3Shanbe.setTypeface(San);
        txt4Shanbe.setTypeface(San);
        txt5Shanbe.setTypeface(San);
        txtJome.setTypeface(San);
        txtClock10.setTypeface(San);
        txtClock20.setTypeface(San);
        txtClock30.setTypeface(San);
        txtClock40.setTypeface(San);
        txtClock50.setTypeface(San);
        txtClock60.setTypeface(San);
        txtClock11.setTypeface(San);
        txtClock21.setTypeface(San);
        txtClock31.setTypeface(San);
        txtClock41.setTypeface(San);
        txtClock51.setTypeface(San);
        txtClock61.setTypeface(San);
        txtClock12.setTypeface(San);
        txtClock22.setTypeface(San);
        txtClock32.setTypeface(San);
        txtClock42.setTypeface(San);
        txtClock52.setTypeface(San);
        txtClock62.setTypeface(San);
        txtClock13.setTypeface(San);
        txtClock23.setTypeface(San);
        txtClock33.setTypeface(San);
        txtClock43.setTypeface(San);
        txtClock53.setTypeface(San);
        txtClock63.setTypeface(San);
        txtClock14.setTypeface(San);
        txtClock24.setTypeface(San);
        txtClock34.setTypeface(San);
        txtClock44.setTypeface(San);
        txtClock54.setTypeface(San);
        txtClock64.setTypeface(San);
        txtClock15.setTypeface(San);
        txtClock25.setTypeface(San);
        txtClock35.setTypeface(San);
        txtClock45.setTypeface(San);
        txtClock55.setTypeface(San);
        txtClock65.setTypeface(San);
        txtClock16.setTypeface(San);
        txtClock26.setTypeface(San);
        txtClock36.setTypeface(San);
        txtClock46.setTypeface(San);
        txtClock56.setTypeface(San);
        txtClock66.setTypeface(San);
        txtToolbar.setTypeface(San);

        SweetLoader();

    }// end define()

    private void SweetLoader(){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("لطفا صبور باشید...");
        pDialog.setCancelable(true);
    }// end SweetLoader()

    private void AskServer(){
        if(isNetworkAvailable()){
            Async_GetSchedual async = new Async_GetSchedual();
            async.mListener = WeekActivity.this;
            async.execute(URL,"TOKEN","CODE","CLASS_ID");
        }else{
            Toast.makeText(WeekActivity.this, getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }// end AskServer()

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float curX, curY;

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mx = event.getX();
                my = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                curX = event.getX();
                curY = event.getY();
                vScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                mx = curX;
                my = curY;
                break;
            case MotionEvent.ACTION_UP:
                curX = event.getX();
                curY = event.getY();
                vScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                hScroll.scrollBy((int) (mx - curX), (int) (my - curY));
                break;
        }

        return true;
    }// end onTouchEvent()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sync, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;

            case R.id.action_sync:
                AskServer();
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStartRequest() {
        pDialog.show();
    }

    @Override
    public void onFinishedRequest(String res) {

    }
}// end class
