package ir.elegam.school.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ir.elegam.school.AsyncTask.GetAttendanceData;
import ir.elegam.school.Classes.CustomTextView;
import ir.elegam.school.Classes.Internet;
import ir.elegam.school.Helper.Attendance;
import ir.elegam.school.Interface.IWebservice;
import ir.elegam.school.R;

public class AttendanceActivity extends AppCompatActivity implements IWebservice
        ,com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog.OnDateSetListener{

    @BindView(R.id.attendance_date)
    CustomTextView attendanceDate;
    @BindView(R.id.attendance_enter_hour)
    CustomTextView attendanceEnterHour;
    @BindView(R.id.attendance_exit_hour)
    CustomTextView attendanceExitHour;
    @BindView(R.id.attendance_status)
    CustomTextView attendanceStatus;
    @BindView(R.id.attendance_choose_date)
    Button attendanceChooseDate;

    PersianCalendar persianCalendar;
    private Toolbar toolbar;
    private Typeface San;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ButterKnife.bind(this);

        define();


        // call webservice if internet is available
        if (Internet.isNetworkAvailable(AttendanceActivity.this)) {
//            GetAttendanceData getdata = new GetAttendanceData(AttendanceActivity.this, AttendanceActivity.this);
//            getdata.execute();
        }
        else {
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.attendance_relative), "اتصال اینترنت خود را چک نمایید", Snackbar.LENGTH_LONG);
            snackbar.show();
        }

    }

    private void define(){
        San = Typeface.createFromAsset(getAssets(), "fonts/SansLight.ttf");
        toolbar = (Toolbar) findViewById(R.id.toolbar_attendance);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        attendanceChooseDate.setTypeface(San);
        TextView txtToolbar = (TextView) findViewById(R.id.txtToolbar_appbar);
        txtToolbar.setText("حضور و غیاب");
        txtToolbar.setTypeface(San);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            startActivity(new Intent(AttendanceActivity.this, ProfileActivity.class));
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AttendanceActivity.this, ProfileActivity.class));
        finish();
    }

    @Override
    public void getResult(Object result) throws Exception {

        Attendance attendance = (Attendance)result;

        attendanceDate.setText(attendance.getToday_date());
        attendanceEnterHour.setText(attendance.getEnter_time());
        attendanceExitHour.setText(attendance.getExit_time());
        attendanceStatus.setText(attendance.getStatus());
    }

    @Override
    public void getError(String ErrorCodeTitle) throws Exception {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.attendance_relative), "مشکلی پیش آمده است . مجددا تلاش نمایید", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @OnClick(R.id.attendance_choose_date)
    public void onClick() {
        persianCalendar = new PersianCalendar();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                AttendanceActivity.this,
                persianCalendar.getPersianYear(),
                persianCalendar.getPersianMonth(),
                persianCalendar.getPersianDay()
        );
        datePickerDialog.show(getFragmentManager(),"Datepickerdialog");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        // call webservice if internet is available

        if (Internet.isNetworkAvailable(AttendanceActivity.this)) {
//            GetAttendanceData getdata = new GetAttendanceData(AttendanceActivity.this, AttendanceActivity.this
//            ,year+"/"+monthOfYear+"/"+dayOfMonth );
//            getdata.execute();
        }
        else {
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.attendance_relative), "اتصال اینترنت خود را چک نمایید", Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
}
