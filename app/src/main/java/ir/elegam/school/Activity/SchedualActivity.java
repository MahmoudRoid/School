package ir.elegam.school.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ir.elegam.school.R;

public class SchedualActivity extends AppCompatActivity {

    private Typeface San;
    private TextView txtToolbar;
    private Toolbar toolbar;
    private Button btnWeek, btnExam, btnAzmoon, btnMulti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedual);
        define();
        loadOnClick();


    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.appbar_schedual);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        btnWeek = (Button) findViewById(R.id.btnWeek_schedual);
        btnExam = (Button) findViewById(R.id.btnExam_schedual);
        btnAzmoon = (Button) findViewById(R.id.btnAzmoon_schedual);
        btnMulti = (Button) findViewById(R.id.btnMulti_schedual);

        txtToolbar.setText("تقویم آموزشی");
        txtToolbar.setTypeface(San);
        btnWeek.setTypeface(San);
        btnExam.setTypeface(San);
        btnAzmoon.setTypeface(San);
        btnMulti.setTypeface(San);

    }// end define()

    private void loadOnClick(){

        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SchedualActivity.this, WeekActivity.class));
            }
        });

        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SchedualActivity.this, BarnameActivity.class);
                intent.putExtra("what","برنامه امتحانات");
                startActivity(intent);
            }
        });

        btnAzmoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SchedualActivity.this, BarnameActivity.class);
                intent.putExtra("what","برنامه آزمون ها");
                startActivity(intent);
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SchedualActivity.this, BarnameActivity.class);
                intent.putExtra("what","کلاس های فوق برنامه");
                startActivity(intent);
            }
        });

    }// end loadOnClick()







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
