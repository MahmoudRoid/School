package ir.elegam.school.Helper;

public class Attendance {
    public String today_date,enter_time,exit_time,status;
    
    public Attendance(String today_date,String enter_time,String exit_time,String status){
        this.today_date=today_date;
        this.enter_time=enter_time;
        this.exit_time=exit_time;
        this.status=status;
    }

    public String getToday_date(){return  this.today_date;}
    public String getEnter_time(){return  this.enter_time;}
    public String getExit_time(){return  this.exit_time;}
    public String getStatus(){return  this.status;}
}
