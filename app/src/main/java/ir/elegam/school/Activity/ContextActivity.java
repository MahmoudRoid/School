package ir.elegam.school.Activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.elegam.school.Classes.Variables;
import ir.elegam.school.Database.database;
import ir.elegam.school.R;

public class ContextActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Typeface San;
    private TextView txtToolbar, txtTitle, txtContent;
    private LinearLayout Lay;
    private String id,Title,Matn,ImageUrl,Fav,Date;
    private Menu menu;
    private database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);
        define();
        initialize();

    }// end onCreate()

    private void define(){
        db = new database(this);
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_Context);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtTitle = (TextView) findViewById(R.id.txtTitle_context);
        txtContent = (TextView) findViewById(R.id.txtContent_context);
        Lay = (LinearLayout) findViewById(R.id.layNews_context);

        txtToolbar.setTypeface(San);
        txtTitle.setTypeface(San);
        txtContent.setTypeface(San);
        txtToolbar.setText("چکیده خبر");

    }// end define()

    private boolean initialize(){
        boolean isFav;
        isFav = getIntent().getBooleanExtra("isFav",false);
        id = getIntent().getStringExtra("id");
        Title = getIntent().getStringExtra("title");
        Matn = getIntent().getStringExtra("matn");
        ImageUrl = getIntent().getStringExtra("url");
        if(isFav){
            Fav = "1";
        }else{
            Fav = "0";
        }
        Date = getIntent().getStringExtra("date");
        txtTitle.setText(Title);
        txtContent.setText(Matn);
        Log.i(Variables.Tag,"id: "+id+" \ntitle: "+Title+" \nmatn: "+Matn+" \nurl: "+ImageUrl+ " \nFav: "+Fav+" \ndate: "+Date);
        return isFav;
    }// end initialize()














    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_empty, menu);
        this.menu = menu;
        if(Fav.equals("0")){
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.favorite_outline_white));
        }else{
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.favorite_white));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch(id){
            case android.R.id.home:
                finish();
                break;

            case R.id.action_favorite:
                if(Fav.equals("0")){
                    try{
                        db.open();
                        db.update("Favorite","1","Sid",this.id);
                        db.close();
                        menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.favorite_white));
                        Fav = "1";
                    }catch (Exception e) { e.printStackTrace(); }
                }else if(Fav.equals("1")){
                    try{
                        db.open();
                        db.update("Favorite","0","Sid",this.id);
                        db.close();
                        menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.favorite_outline_white));
                        Fav = "0";
                    }catch (Exception e) { e.printStackTrace(); }
                }

                break;

            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}// end class