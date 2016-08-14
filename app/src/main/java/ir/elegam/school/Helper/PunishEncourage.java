package ir.elegam.school.Helper;

/**
 * Created by Droid on 8/14/2016.
 */
public class PunishEncourage {
    public String date,description;

    public PunishEncourage(String date,String description){
        this.date=date;
        this.description=description;
    }

    public String getDate(){return  this.date;}
    public String getDescription(){return  this.description;}
}
