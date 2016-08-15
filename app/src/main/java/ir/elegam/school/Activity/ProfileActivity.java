package ir.elegam.school.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.Classes.Variables;
import ir.elegam.school.R;

public class ProfileActivity extends AppCompatActivity implements BottomSheetListener {

    private FloatingActionButton fab;
    private LinearLayout lay1,lay2,lay3,lay4,lay5,lay6,lay7,lay8,lay9,lay10;
    private TextView txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10,txtSentences,txtStudent;
    private Typeface San;
    private ImageView ivSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        define();
        OnClickLoader();


    }// end onCreate()

    private void define(){
        MakeRoots();
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        fab = (FloatingActionButton) findViewById(R.id.fab_profile);
        lay1 = (LinearLayout) findViewById(R.id.lay1_profile);
        lay2 = (LinearLayout) findViewById(R.id.lay2_profile);
        lay3 = (LinearLayout) findViewById(R.id.lay3_profile);
        lay4 = (LinearLayout) findViewById(R.id.lay4_profile);
        lay5 = (LinearLayout) findViewById(R.id.lay5_profile);
        lay6 = (LinearLayout) findViewById(R.id.lay6_profile);
        lay7 = (LinearLayout) findViewById(R.id.lay7_profile);
        lay8 = (LinearLayout) findViewById(R.id.lay8_profile);
        lay9 = (LinearLayout) findViewById(R.id.lay9_profile);
        lay10 = (LinearLayout) findViewById(R.id.lay10_profile);
        txt1 = (TextView) findViewById(R.id.txt1_profile);
        txt2 = (TextView) findViewById(R.id.txt2_profile);
        txt3 = (TextView) findViewById(R.id.txt3_profile);
        txt4 = (TextView) findViewById(R.id.txt4_profile);
        txt5 = (TextView) findViewById(R.id.txt5__profile);
        txt6 = (TextView) findViewById(R.id.txt6__profile);
        txt7 = (TextView) findViewById(R.id.txt7__profile);
        txt8 = (TextView) findViewById(R.id.txt8_profile);
        txt9 = (TextView) findViewById(R.id.txt9_profile);
        txt10 = (TextView) findViewById(R.id.txt10_profile);
        txtSentences = (TextView) findViewById(R.id.txtSentences_profile);
        txtStudent = (TextView) findViewById(R.id.txtStudent_profile);
        ivSettings = (ImageView) findViewById(R.id.ivSettings_profile);

        txt1.setTypeface(San);
        txt2.setTypeface(San);
        txt3.setTypeface(San);
        txt4.setTypeface(San);
        txt5.setTypeface(San);
        txt6.setTypeface(San);
        txt7.setTypeface(San);
        txt8.setTypeface(San);
        txt9.setTypeface(San);
        txt10.setTypeface(San);
        txtSentences.setTypeface(San);
        txtStudent.setTypeface(San);

    }// end define()

    private void OnClickLoader(){

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BottomSheet.Builder(ProfileActivity.this)
                        .setSheet(R.menu.menu_button_sheet)
                        .setTitle("")
                        .grid()
                        .setListener(ProfileActivity.this)
                        .show();
            }
        });

        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SchedualActivity.class));
            }
        });

        lay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lay8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lay9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lay10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SettingActivity.class));
            }
        });


    }// end OnClickLoader()

    @Override
    public void onSheetShown(@NonNull BottomSheet bottomSheet) {
        Log.v(Variables.Tag, "onSheetShown");
    }

    @Override
    public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem) {
        Log.v(Variables.Tag, "onSheetItemSelected");
    }

    @Override
    public void onSheetDismissed(@NonNull BottomSheet bottomSheet, @DismissEvent int dismissEvent) {
        Log.i(Variables.Tag, "onSheetDismissed " + dismissEvent);

        switch (dismissEvent) {
            case BottomSheetListener.DISMISS_EVENT_BUTTON_POSITIVE:
                Toast.makeText(getApplicationContext(), "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                break;

            case BottomSheetListener.DISMISS_EVENT_BUTTON_NEGATIVE:
                Toast.makeText(getApplicationContext(), "Negative Button Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_linkedIn:
                Toast.makeText(ProfileActivity.this, "LinkedIn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_instagram:
                Toast.makeText(ProfileActivity.this, "INSTAGRAM", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_googleplus:
                Toast.makeText(ProfileActivity.this, "Google+", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_website:
                Toast.makeText(ProfileActivity.this, "Website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_favorites:
                startActivity(new Intent(ProfileActivity.this, FavoriteActivity.class));
                break;
            case R.id.action_exit:
                finish();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("اخطار")
                .setContentText("مایل به خروج هستید ؟")
                .setConfirmText("بله")
                .setCancelText("خیر")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();
                        moveTaskToBack(true);
                        finish();
                        System.exit(0);
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sw) {
                        sw.dismiss();
                    }
                })
                .show();
    }

    private void MakeRoots(){
        File root = new File(Environment.getExternalStorageDirectory(),"SCHOOL");
        File Fimage = new File(Variables.ROOT,"images");
        File Fvideo = new File(Variables.ROOT,"videos");
        File Fpdf = new File(Variables.ROOT,"PDFs");
        if(!root.exists())
        {
            root.   mkdir();
            Fimage. mkdir();
            Fvideo. mkdir();
            Fpdf.   mkdir();
        }
    }// end MakeRoots()

}// end class
