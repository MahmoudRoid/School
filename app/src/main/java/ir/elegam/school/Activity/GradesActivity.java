package ir.elegam.school.Activity;

import android.content.Context;
import android.graphics.Color;
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
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.Adapter.Schedual_Adapter;
import ir.elegam.school.AsyncTask.Async_GetSchedual;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.Classes.Variables;
import ir.elegam.school.Helper.Object_Schdual;
import ir.elegam.school.R;

public class GradesActivity extends AppCompatActivity implements Async_GetSchedual.GetSchedual{

    private Toolbar toolbar;
    private Typeface San;
    private TextView txtToolbar;
    private Spinner spr;
    private RecyclerView rv;
    private SweetAlertDialog pDialog;
    private Schedual_Adapter mAdapter;
    private List<Object_Schdual> mylist = new ArrayList<>();
    private String URL = URLS.WEB_SERVICE_URL, selected_dars="";
    private String[] DarsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grades);
        define();

        spr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                selected_dars = DarsList[position];
                //AskServer();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> aIntru = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DarsList) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                ((TextView) v).setTypeface(San);
                return v;
            }
            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                View v =super.getDropDownView(position, convertView, parent);
                ((TextView) v).setTypeface(San);
                return v;
            }
        };
        spr.setAdapter(aIntru);

        spr.setAdapter(aIntru);

    }// end define()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_grades);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar_spinner);
        spr = (Spinner) findViewById(R.id.spr_appbar_spinner);
        rv = (RecyclerView) findViewById(R.id.rv_grades);

        rv = (RecyclerView) findViewById(R.id.rv_grades);
        LinearLayoutManager LM = new LinearLayoutManager(GradesActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(LM);

        txtToolbar.setTypeface(San);

        SweetLoader();
        String f_method = getIntent().getStringExtra("darslist");
        Log.i(Variables.Tag,"darslist: "+f_method);
        ParsString(f_method);

    }// end define()

    private void SweetLoader(){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("لطفا صبور باشید...");
        pDialog.setCancelable(true);
    }// end SweetLoader()

    private void ParsString(String str){
        Log.i(Variables.Tag,"in Pars String");
        ArrayList<String> TempList = new ArrayList<>();
        TempList.add("انتخاب نام درس");
        int c1=0;
        String temp;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='|'){
                temp = str.substring(c1,i);
                TempList.add(temp);
                Log.i(Variables.Tag,"temp: "+temp);
                c1=i+1;
            }
        }
        int iTemp = TempList.size();
        DarsList = new String[iTemp];
        for(int i=0;i<iTemp;i++){
            DarsList[i]=TempList.get(i);
        }
    }// end ParsString()

    private void SetRecyclerView(){
        mAdapter = new Schedual_Adapter(mylist,San,getApplicationContext());
        rv.setAdapter(mAdapter);
    }// end SetRecyclerView()

    private void AskServer(){
        if(isNetworkAvailable()){
            Async_GetSchedual async = new Async_GetSchedual();
            async.mListener = GradesActivity.this;
            async.execute(URL,"TOKEN","CODE","CLASS_ID",selected_dars);
        }else{
            Toast.makeText(GradesActivity.this, getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }// end AskServer()

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()

    @Override
    public void onStartRequest() {
        pDialog.show();
    }

    @Override
    public void onFinishedRequest(String res) {
        pDialog.dismiss();
        mylist.clear();
        // mAdapter.clear();
        // get and parse data from server
        SetRecyclerView();
    }







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
