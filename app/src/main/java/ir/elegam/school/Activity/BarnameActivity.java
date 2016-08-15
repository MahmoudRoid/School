package ir.elegam.school.Activity;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.Adapter.Schedual_Adapter;
import ir.elegam.school.AsyncTask.Async_GetSchedual;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.Classes.Variables;
import ir.elegam.school.Helper.Object_Schdual;
import ir.elegam.school.R;

public class BarnameActivity extends AppCompatActivity implements Async_GetSchedual.GetSchedual{

    private Toolbar toolbar;
    private Typeface San;
    private TextView txtToolbar;
    private String What="", URL= URLS.WEB_SERVICE_URL, DOWNLOAD_LINK="";
    private RecyclerView rv;
    private Schedual_Adapter mAdapter;
    private List<Object_Schdual> mylist = new ArrayList<>();
    private SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barname);
        define();
        AskServer();

    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_barname);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        What = getIntent().getStringExtra("what");

        rv = (RecyclerView) findViewById(R.id.rv_barname);
        LinearLayoutManager LM = new LinearLayoutManager(BarnameActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(LM);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setText(What);
        txtToolbar.setTypeface(San);

        SweetLoader();

    }// end define()

    private void SweetLoader(){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("لطفا صبور باشید...");
        pDialog.setCancelable(true);
    }// end SweetLoader()

    private void SetRecyclerView(){
        mAdapter = new Schedual_Adapter(mylist,San,getApplicationContext());
        rv.setAdapter(mAdapter);
    }// end SetRecyclerView()

    private void AskServer(){
        if(isNetworkAvailable()){
            Async_GetSchedual async = new Async_GetSchedual();
            async.mListener = BarnameActivity.this;
            async.execute(URL,"TOKEN","CODE","CLASS_ID",What);
        }else{
            Toast.makeText(BarnameActivity.this, getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }// end AskServer()

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()

    public void file_download(String uRl,String nameoffile) {
        File direct = new File(Variables.PDF);
        if (!direct.exists()) {
            direct.mkdirs();
        }
        File file = new File(direct, nameoffile);
        if (file.exists()) {
            show(nameoffile);
        } else
        {
            DownloadManager mgr = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
            Uri downloadUri = Uri.parse(uRl);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_WIFI
                            | DownloadManager.Request.NETWORK_MOBILE)
                    .setAllowedOverRoaming(false).setTitle("دانلود pdf")
                    .setDestinationInExternalPublicDir("/SCHOOL/PDFs", nameoffile);
            mgr.enqueue(request);
        }
    }// end file_download()

    public void show(String filename)
    {
        File file = new File(Variables.PDF +"/"+ filename);
        Intent target = new Intent(Intent.ACTION_VIEW);
        target.setDataAndType(Uri.fromFile(file),"application/pdf");
        target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

        Intent intent = Intent.createChooser(target, "Open File");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) { e.printStackTrace(); }
    }// end show()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_download, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;

            case R.id.action_download:
                file_download(DOWNLOAD_LINK,What);
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
        pDialog.dismiss();
        // parse JSON
        SetRecyclerView();
    }

}// end class
