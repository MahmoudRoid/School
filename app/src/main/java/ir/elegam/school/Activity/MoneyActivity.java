package ir.elegam.school.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.AsyncTask.Async_GetSchedual;
import ir.elegam.school.AsyncTask.Async_Tuition;
import ir.elegam.school.Classes.URLS;
import ir.elegam.school.R;

public class MoneyActivity extends AppCompatActivity implements Async_Tuition.GetTuition{

    private Toolbar toolbar;
    private TextView txtToolbar,txtTitle,txtStatus;
    private Typeface San;
    private Button btnPay;
    private SharedPreferences prefs;
    private String UsserId="",UsserName="",Date="",YearTuition="",PayedTuition="",RemainingTuition="",Pay_Url;
    private SweetAlertDialog pDialog;
    private String URL= URLS.WEB_SERVICE_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        define();
        init();
        AskServer();

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Pay_Url.equals("")){
                    Toast.makeText(MoneyActivity.this, "شما در وضعیت پرداخت کل شهریه به سر میبرید.", Toast.LENGTH_SHORT).show();
                }else{
                    DialogChoose();
                }
            }
        });

    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_money);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtTitle = (TextView) findViewById(R.id.txtTitle_money);
        txtStatus = (TextView) findViewById(R.id.txtStatus_money);
        btnPay = (Button) findViewById(R.id.btnPay_money);
        txtToolbar.setText("امور مالی");
        txtToolbar.setTypeface(San);
        txtStatus.setTypeface(San);
        txtTitle.setTypeface(San);
        btnPay.setTypeface(San);

        prefs = getApplicationContext().getSharedPreferences("school_shared", 0);
        UsserId = prefs.getString("usser_id","123");
        UsserName = prefs.getString("usser_name","حسن مرادی");

        SweetLoader();

    }// end define()

    private void SweetLoader(){
        pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("لطفا صبور باشید...");
        pDialog.setCancelable(true);
    }// end SweetLoader()

    private void init(){
        txtTitle.setText("ولی محترم دانش آموز"+ UsserName +"\n"+
        "شهریه سال تحصیلی "+Date+
                " فرزند شما"+YearTuition+
                " میباشد."+"\n\n\n\n\n"
        );

        txtStatus.setText("مبلغ پرداخت شده : "+ PayedTuition +"\n\n"+
        "باقیمانده : "+ RemainingTuition + "\n\n\n");
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

    @Override
    public void onStartRequest() {
        pDialog.show();
    }

    @Override
    public void onFinishedRequest(String res) {
        pDialog.dismiss();
        // parse data and fill parameters
        Pay_Url="";
        Date="";
        YearTuition="";
        PayedTuition="";
        RemainingTuition="";
    }

    private void AskServer(){
        if(isNetworkAvailable()){
            Async_Tuition async = new Async_Tuition();
            async.mListener = MoneyActivity.this;
            async.execute(URL,"TOKEN","CODE",UsserId);
        }else{
            Toast.makeText(MoneyActivity.this, getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
        }
    }// end AskServer()

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()

    private void DialogChoose() {
        final Dialog d = new Dialog(this);
        d.setCancelable(true);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.dialog_pay);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = d.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        final TextView txtContext = (TextView) d.findViewById(R.id.txtQuestion_dialog);
        final TextView txtPay = (TextView) d.findViewById(R.id.txtPay_dialog);
        final EditText edtPay = (EditText) d.findViewById(R.id.edtPay_dialog);

        txtContext.setTypeface(San);
        txtPay.setTypeface(San);
        edtPay.setTypeface(San);

        txtContext.setText("آیا مایلید باقی شهریه را بصورت آنلاین پرداخت نمایید؟");
        txtPay.setText("پرداخت");
        edtPay.setText(RemainingTuition);

        txtPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = Pay_Url;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        d.show();
    }// end DialogChoose()

}// end class
