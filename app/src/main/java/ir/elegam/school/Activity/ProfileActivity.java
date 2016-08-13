package ir.elegam.school.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kennyc.bottomsheet.BottomSheet;
import com.kennyc.bottomsheet.BottomSheetListener;

import ir.elegam.school.Classes.Variables;
import ir.elegam.school.R;

public class ProfileActivity extends AppCompatActivity implements BottomSheetListener {

    private FloatingActionButton fab;
    private LinearLayout lay1,lay2,lay3,lay4,lay5,lay6,lay7,lay8,lay9,lay10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        define();
        OnClickLoader();


    }// end onCreate()

    private void define(){
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
            case R.id.action_instagram:
                Toast.makeText(ProfileActivity.this, "INSTAGRAM", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}// end class
