package ir.elegam.school.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

        // Activity Present
        lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, AttendanceActivity.class));
            }
        });

        // Activity Consult
        lay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, CriticSuggestionActivity.class));
            }
        });

        // Activity News
        lay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, NewsActivity.class);
                intent.putExtra("faction","news");
                startActivity(intent);
            }
        });

        // Activity Grade list
        lay4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, GradeListActivity.class);
                startActivity(intent);
            }
        });

        // Activity Schedual list
        lay5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SchedualActivity.class));
            }
        });

        // Activity Punish and Encourage
        lay6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, PunishEncourageActivity.class));
            }
        });

        lay7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Dialog Choose Which Gallery
        lay8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooseDialog();
            }
        });

        lay9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        // Activity Money
        lay10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, MoneyActivity.class));
            }
        });

        // Activity Settings
        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SettingActivity.class));
            }
        });


    }// end OnClickLoader()

    private void showChooseDialog() {
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

        final ImageView img=(ImageView)d.findViewById(R.id.ivHeader_dialog);
        final TextView txtHeader = (TextView) d.findViewById(R.id.txtHeader_dialog);
        final TextView txtContext = (TextView) d.findViewById(R.id.txtContext_dialog);
        final TextView txtOne = (TextView) d.findViewById(R.id.txtOne_dialog);
        final TextView txtTwo = (TextView) d.findViewById(R.id.txtTwo_dialog);
        final TextView txtThree = (TextView) d.findViewById(R.id.txtThree_dialog);

        img.setVisibility(View.INVISIBLE);
        txtHeader.setTypeface(San);
        txtContext.setTypeface(San);
        txtOne.setTypeface(San);
        txtTwo.setTypeface(San);
        txtThree.setTypeface(San);

        txtHeader.setText("انتخاب گالری");
        txtContext.setText("");
        txtOne.setText("تصاویر");
        txtTwo.setVisibility(View.INVISIBLE);
        txtThree.setText("ویدیو");

        txtHeader.setGravity(Gravity.CENTER_HORIZONTAL);

        txtThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss();

            }
        });

        txtOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            d.dismiss();
            }
        });
        d.show();

    }

    @Override
    public void onSheetShown(@NonNull BottomSheet bottomSheet) {
        Log.v(Variables.Tag, "onSheetShown");
    }

    @Override
    public void onSheetItemSelected(@NonNull BottomSheet bottomSheet, MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_aboutus:
                startActivity(new Intent(ProfileActivity.this, AboutusActivity.class));
                break;
            case R.id.action_contact_intro:
                startActivity(new Intent(ProfileActivity.this, ContactSchool.class));
                break;
            case R.id.action_guide:
                startActivity(new Intent(ProfileActivity.this, GuideActivity.class));
                break;
            case R.id.action_honor:
                Intent intent = new Intent(ProfileActivity.this, NewsActivity.class);
                intent.putExtra("faction","honor");
                startActivity(intent);
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
