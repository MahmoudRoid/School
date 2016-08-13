package ir.elegam.school.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import ir.elegam.school.AsyncTask.Login;
import ir.elegam.school.Classes.Internet;
import ir.elegam.school.Classes.MyApplication;
import ir.elegam.school.Interface.IWebservice;
import ir.elegam.school.R;

public class LoginActivity extends AppCompatActivity implements IWebservice{

    @BindView(R.id.username_edt)
    AppCompatEditText usernameEdt;
    @BindView(R.id.password_edt)
    AppCompatEditText passwordEdt;
    @BindView(R.id.signin_btn)
    AppCompatButton signinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signin_btn)
    public void onClick() {

//        boolean filled = true;
//        // checck if edit esiername is filled
//        if(usernameEdt.getText().toString().trim().equals("")){
//            filled=false;
//            usernameEdt.setError("por kon");
//        }
//        // checck if edit password is filled
//        if(passwordEdt.getText().toString().trim().equals("")){
//            filled=false;
//            passwordEdt.setError("por kon");
//        }
//
//        if(filled==true && (!usernameEdt.getText().toString().trim().equals("") && (!passwordEdt.getText().toString().trim().equals("")))) {
//            // send data to server
//            // if net is available
//            if(Internet.isNetworkAvailable(LoginActivity.this)){
//                Login login=new Login(LoginActivity.this,LoginActivity.this,usernameEdt.getText().toString().trim(),passwordEdt.getText().toString().trim());
//                login.execute();
//            }
//        }

        startActivity(new Intent(LoginActivity.this,ProfileActivity.class));

    }

    @Override
    public void getResult(Object result) throws Exception {
        // goto next activity
//        startActivity(new Intent(LoginActivity.this,xxx.class));

        SharedPreferences.Editor editor = getSharedPreferences("school_shared", MODE_PRIVATE).edit();
        editor.putBoolean("has_login",true);
        editor.commit();
    }

    @Override
    public void getError(String ErrorCodeTitle) throws Exception {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.login_relative), "motasefane moshkel dare", Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // todo : add exit diolog
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
}
