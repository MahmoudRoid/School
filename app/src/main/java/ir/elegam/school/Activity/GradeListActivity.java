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

public class GradeListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Typeface San;
    private TextView txtToolbar;
    private Button btnClass,btnKarname,btnAzmoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_list);
        define();
        onCLickLoader();

    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_gradelist);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        btnClass = (Button) findViewById(R.id.btnClass_gradelist);
        btnKarname = (Button) findViewById(R.id.btnKarname_gradelist);
        btnAzmoon = (Button) findViewById(R.id.btnAzmoon_gradelist);

        txtToolbar.setTypeface(San);
        btnAzmoon.setTypeface(San);
        btnKarname.setTypeface(San);
        btnClass.setTypeface(San);

    }// end define()

    private void onCLickLoader(){

        btnClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GradeListActivity.this, GradesActivity.class);
                intent.putExtra("darslist","ریاضی|علوم|ورزش|مدنی|دینی|هنر|");
                startActivity(intent);
            }
        });

        btnKarname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GradeListActivity.this, ArticleActivity.class);
                intent.putExtra("what","کارنامه ها");
                startActivity(intent);
            }
        });

        btnAzmoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GradeListActivity.this, ArticleActivity.class);
                intent.putExtra("what","نتایج آزمون");
                startActivity(intent);
            }
        });

    }// end onCLickLoader()

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
