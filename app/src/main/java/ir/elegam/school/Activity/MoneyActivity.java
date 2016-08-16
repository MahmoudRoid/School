package ir.elegam.school.Activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import ir.elegam.school.R;

public class MoneyActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView txtToolbar;
    private Typeface San;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money);
        define();

    }// end onCreate()

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_money);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setText("امور مالی");
        txtToolbar.setTypeface(San);

    }// end define()

}// end class
