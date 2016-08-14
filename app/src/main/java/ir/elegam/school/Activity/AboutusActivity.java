package ir.elegam.school.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import ir.elegam.school.R;

public class AboutusActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView ivTelegram, ivInstagram, ivWebsite, ivCall;
    private TextView txtAboutus, txtToolbar;
    private FloatingActionButton fab;
    private Typeface San;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        define();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("vnd.android.cursor.dir/email");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"info@egam.ir"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"دپارتمان اپلیکیشن");
                startActivity(Intent.createChooser(emailIntent, "فرستادن ایمیل از طریق :"));

            }
        });

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "02632775520", null));
                startActivity(intent);
            }
        });

        ivWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://egam.ir/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        ivInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.instagram.com/_u/elegaaam");
                Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                likeIng.setPackage("com.instagram.android");

                try { startActivity(likeIng); }
                catch (ActivityNotFoundException e) {
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://www.instagram.com/elegaaam"))
                    );
                }
            }
        });

        ivTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telegram = new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://telegram.me/elegam")
                );
                startActivity(telegram);
            }
        });


    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_aboutus);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ivTelegram = (ImageView) findViewById(R.id.ivTelegram_aboutus);
        ivInstagram = (ImageView) findViewById(R.id.ivInstagram_aboutus);
        ivWebsite = (ImageView) findViewById(R.id.ivWebsite_aboutus);
        ivCall = (ImageView) findViewById(R.id.ivCal_aboutus);
        fab = (FloatingActionButton) findViewById(R.id.fab_aboutus);
        txtAboutus = (TextView) findViewById(R.id.txt_aboutus);
        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);

        txtAboutus.setTypeface(San);
        txtToolbar.setTypeface(San);

    }// end define()





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_aboutus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;

            case R.id.action_share:
                try{
                    ArrayList<Uri> uris = new ArrayList<>();
                    Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    sendIntent.setType("application/*");
                    uris.add(Uri.fromFile(new File(getApplicationInfo().publicSourceDir)));
                    sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
                    startActivity(Intent.createChooser(sendIntent, null));
                }catch(Exception e){e.printStackTrace();}
                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}// end class

