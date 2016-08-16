package ir.elegam.school.Activity;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.afollestad.easyvideoplayer.EasyVideoCallback;
import com.afollestad.easyvideoplayer.EasyVideoPlayer;
import java.io.File;
import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.AsyncTask.Async_Video_Downloader;
import ir.elegam.school.Classes.Variables;
import ir.elegam.school.R;


public class ShowVideoActivity extends AppCompatActivity implements EasyVideoCallback , Async_Video_Downloader.GetVideo {

    private EasyVideoPlayer player;
    private String VIDEO_URL, VIDEO_NAME;
    private Toolbar toolbar;
    private Typeface San;
    private TextView txtToolbar;
    private boolean isVideoExists;
    SweetAlertDialog pDialog ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);
        Log.i(Variables.Tag,"in SHowActivity");
        define();

        player.setCallback(this);
        player.setCustomLabelText(VIDEO_NAME);
        if(isVideoExists){
            File file = new File(Variables.VIDEO+"/"+VIDEO_NAME+".mp4");
            Log.i(Variables.Tag,"file path: "+file.getAbsolutePath());
            player.setSource(Uri.fromFile(file));
        }else{
            Log.i(Variables.Tag,"file url: "+VIDEO_URL);
            player.setSource(Uri.parse(VIDEO_URL));
        }

    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_showvideo);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pDialog = new SweetAlertDialog(ShowVideoActivity.this, SweetAlertDialog.PROGRESS_TYPE);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setTypeface(San);
        player = (EasyVideoPlayer) findViewById(R.id.player);
        VIDEO_NAME = getIntent().getStringExtra("name");
        VIDEO_URL = getIntent().getStringExtra("url");
        txtToolbar.setText(VIDEO_NAME);
        isVideoExists = getIntent().getBooleanExtra("exist",false);

        ArcLoader();

    }// end define()

    private void ArcLoader(){
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("در حال دریافت اطلاعات");
        pDialog.setCancelable(false);
//        pDialog.show();
    }// end ArcLoader()


    @Override
    public void onPause() {
        super.onPause();
        // Make sure the player stops playing if the user presses the home button.
        player.pause();
    }

    @Override
    public void onStarted(EasyVideoPlayer player) {

    }

    @Override
    public void onPaused(EasyVideoPlayer player) {

    }

    @Override
    public void onPreparing(EasyVideoPlayer player) {

    }

    @Override
    public void onPrepared(EasyVideoPlayer player) {

    }

    @Override
    public void onBuffering(int percent) {

    }

    @Override
    public void onError(EasyVideoPlayer player, Exception e) {

    }

    @Override
    public void onCompletion(EasyVideoPlayer player) {

    }

    @Override
    public void onRetry(EasyVideoPlayer player, Uri source) {

    }

    @Override
    public void onSubmit(EasyVideoPlayer player, Uri source) {

    }

    @Override
    public void onStratVideoDownload() {
        pDialog.show();
    }

    @Override
    public void onFinishedVideoDownload(String res) {
        pDialog.dismiss();
        Toast.makeText(ShowVideoActivity.this, "دانلود شما با موفقیت به اتمام رسید!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressUpdate(int value) {

    }

    private void DownloadVideo(){

        Async_Video_Downloader async_video_downloader = new Async_Video_Downloader();
        async_video_downloader.mListener = ShowVideoActivity.this;
        pDialog.show();
        async_video_downloader.execute(VIDEO_URL,VIDEO_NAME);

    }// end DownloadVideo()

    public void file_download(String uRl,String nameoffile) {


        nameoffile+=".mp4";
        File direct = new File(Variables.VIDEO);

        if (!direct.exists()) {
            direct.mkdirs();
        }

        File file = new File(direct, nameoffile);
        if (file.exists()) {
            Toast.makeText(ShowVideoActivity.this, "قبلا این ویدئو را دانلود کردید!", Toast.LENGTH_SHORT).show();
        }
        else {
            DialogChoose(nameoffile,uRl);
        }
    }// end file_download()

    private void DialogChoose(final String fileName,final String fileUrl) {
        final Dialog d = new Dialog(this);
        d.setCancelable(true);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.dialog_choose);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = d.getWindow();
        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        final TextView txtHeader = (TextView) d.findViewById(R.id.txtHeader_dialog);
        final TextView txtContext = (TextView) d.findViewById(R.id.txtContext_dialog);
        final TextView txtOne = (TextView) d.findViewById(R.id.txtOne_dialog);
        final TextView txtTwo = (TextView) d.findViewById(R.id.txtTwo_dialog);
        final TextView txtThree = (TextView) d.findViewById(R.id.txtThree_dialog);

        txtHeader.setTypeface(San);
        txtContext.setTypeface(San);
        txtOne.setTypeface(San);
        txtTwo.setTypeface(San);
        txtThree.setTypeface(San);

        txtHeader.setText("فایل ناموجود");
        txtContext.setText("آیا میخواهید فایل را دانلود کنید؟");
        txtOne.setText("بله");
        txtTwo.setVisibility(View.INVISIBLE);
        txtThree.setText("خیر");

        txtThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();

            }
        });

        txtOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()){
                    d.dismiss();
                    DownloadManager mgr = (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                    Uri downloadUri = Uri.parse(fileUrl);
                    DownloadManager.Request request = new DownloadManager.Request(downloadUri);
                    request.setAllowedNetworkTypes(
                            DownloadManager.Request.NETWORK_WIFI
                                    | DownloadManager.Request.NETWORK_MOBILE)
                            .setAllowedOverRoaming(false).setTitle("دانلود ویدئو")
                            .setDestinationInExternalPublicDir("/SCHOOL/videos", fileName);
                    mgr.enqueue(request);
                }else{
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
                }
            }
        });
        d.show();
    }// end DialogChoose()

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }// isNetworkAvailable()







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gallary, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;

            case R.id.action_save:
                Log.i(Variables.Tag,"save video");
                //DownloadVideo();
                file_download(VIDEO_URL,VIDEO_NAME);
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}// end class
