package ir.elegam.school.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.AsyncTask.Async_ChangePass;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.R;

public class SettingActivity extends AppCompatActivity implements Async_ChangePass.ChangePass{

    private Toolbar toolbar;
    private TextView txtToolbar,txtTitlePass;
    private Typeface San;
    private EditText edtOldPass,edtNewPass,edtAgainNewPass;
    private TextInputLayout til1,til2,til3;
    private Button btnConfirm;
    private String Old,New,Again,MyPass;
    private SharedPreferences prefs;
    private SweetAlertDialog pDialog;
    private String URL = URLS.WEB_SERVICE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        define();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AttemptValidate();
            }
        });

    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prefs = getApplicationContext().getSharedPreferences("school_shared", 0);
        MyPass = prefs.getString("password","");

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtTitlePass = (TextView) findViewById(R.id.txtTitlePass_setting);
        til1 = (TextInputLayout) findViewById(R.id.til1_setting);
        til2 = (TextInputLayout) findViewById(R.id.til2_setting);
        til3 = (TextInputLayout) findViewById(R.id.til3_setting);
        edtOldPass = (EditText) findViewById(R.id.edtOldPass_setting);
        edtNewPass = (EditText) findViewById(R.id.edtNewPass_setting);
        edtAgainNewPass = (EditText) findViewById(R.id.edtAgainNewPass_setting);
        btnConfirm = (Button) findViewById(R.id.btnConfirm_setting);

        txtToolbar.setTypeface(San);
        txtTitlePass.setTypeface(San);
        til1.setTypeface(San);
        til2.setTypeface(San);
        til3.setTypeface(San);
        edtOldPass.setTypeface(San);
        edtNewPass.setTypeface(San);
        edtAgainNewPass.setTypeface(San);
        btnConfirm.setTypeface(San);
        txtToolbar.setText("تنظیمات");

        SweetLoader();

    }// end define()

    private boolean isInputValid(String data){
        if(data.length() >= 6 && data.length() < 30){
            return true;
        }else{
            return false;
        }
    }// end isInputValid()

    private void AttemptValidate() {
        edtOldPass.setError(null);
        edtNewPass.setError(null);
        edtAgainNewPass.setError(null);

        Old = edtOldPass.getText().toString();
        New = edtNewPass.getText().toString();
        Again = edtAgainNewPass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid Old Password
        if (TextUtils.isEmpty(Old)) {
            edtOldPass.setError(getString(R.string.error_empty));
            focusView = edtOldPass;
            cancel = true;
        } else if (!isInputValid(Old)) {
            edtOldPass.setError(getString(R.string.error_length));
            focusView = edtOldPass;
            cancel = true;
        } else if(!MyPass.equals(Old)){
            edtOldPass.setError(getString(R.string.error_different));
            focusView = edtOldPass;
            cancel = true;
        }

        // Check for a valid New Password
        if (TextUtils.isEmpty(New)) {
            edtNewPass.setError(getString(R.string.error_empty));
            focusView = edtNewPass;
            cancel = true;
        } else if (!isInputValid(New)) {
            edtNewPass.setError(getString(R.string.error_length));
            focusView = edtNewPass;
            cancel = true;
        }

        // Check for a valid Again New Password
        if (TextUtils.isEmpty(Again)) {
            edtAgainNewPass.setError(getString(R.string.error_empty));
            focusView = edtAgainNewPass;
            cancel = true;
        } else if ( !Again.equals(New) ) {
            edtAgainNewPass.setError(getString(R.string.error_different));
            focusView = edtAgainNewPass;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            SendTOServer();
        }
    }// end AttemptValidate()

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()

    private void SweetLoader(){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("لطفا صبور باشید...");
        pDialog.setCancelable(true);
    }// end SweetLoader()

    private void SendTOServer(){
        if (isNetworkAvailable()){
            Async_ChangePass async = new Async_ChangePass();
            async.mListener = SettingActivity.this;
            async.execute(URL,"Contact","TOKEN",Old, New);
        }else{
            Toast.makeText(SettingActivity.this, getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }// end SendTOServer()

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

    @Override
    public void onStartRequest() {
        pDialog.show();
    }

    @Override
    public void onFinishedRequest(String res) {
        pDialog.dismiss();

        // if pass changed in server databse :
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("password",New);
        editor.commit();


    }
}// end class
