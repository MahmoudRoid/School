package ir.elegam.school.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.SQLException;

import ir.elegam.school.Helper.Object_News;

public class database {

    static final String SID			= "Sid";       // 1
    static final String TITLE 		= "Title";     // 2
    static final String CONTEXT 	= "Context";   // 3
    static final String DATE 		= "Date";      // 4
    static final String IMAGEURL 	= "ImageUrl";  // 5
    static final String FAVORITE 	= "Favorite";  // 6
    static final String FACTION 	= "Faction";   // 6

    private String TAG = "MY APP";
    public dbHelper dbhelper;
    public SQLiteDatabase db;

    public database(Context context) {
        dbhelper = new dbHelper(context);
        Log.i(TAG, "context send to helper");
    }

    public void open() throws SQLException{
        db = dbhelper.getWritableDatabase();
        Log.i(TAG, "Database open");
    }

    public void close(){
        db.close();
        Log.i(TAG, "Database close");
    }

    public int countAll(){
        Cursor cu = db.rawQuery("select * from "+ dbhelper.getTableName() +" ;", null);
        int s = cu.getCount();
        Log.i(TAG, "Count is: "+s);
        return s;
    }

    public int countAll(String Column, String Index){
        Cursor cu = db.rawQuery("select * from "+ dbhelper.getTableName() +" where "+ Column +" = '"+ Index +"' ;", null);
        int s = cu.getCount();
        Log.i(TAG, "Count is: "+s);
        return s;
    }

    public int CountInGroup(String Group){
        Cursor cu = db.rawQuery("select * from "+ dbhelper.getTableName() +"  GROUP BY "+Group+" ;", null);
        int s = cu.getCount();
        Log.i(TAG, "Count is: "+s);
        return s;
    }

    public int countSearch(String query){
        Cursor cu = db.rawQuery(query +" ;", null);
        int s = cu.getCount();
        Log.i(TAG, "Count is: "+s);
        return s;
    }

    public boolean CheckExistance(String Column, String Index){
        Cursor cursor = db.rawQuery("select * from "+ dbhelper.getTableName() +" where "+ Column +" = '"+ Index +"';", null);
        cursor.moveToFirst();
        int result = cursor.getCount();
        if(result == 0){
            Log.i(TAG,"false");
            return false;
        }
        else{
            Log.i(TAG,"true");
            return true;
        }
    }

    public String DisplayOne(int field, String Column, String Index){
        Cursor cu = db.rawQuery("select * from " + dbhelper.getTableName() + " where " + Column + " = '" + Index + "' ;", null);
        cu.moveToFirst();
        String result = cu.getString(field);
        return result;
    }// end Display()

    public String DisplayAll(int row, int field, String Column, String Index){
        Cursor cu = db.rawQuery("select * from "+ dbhelper.getTableName() +" where "+ Column +" = '"+ Index +"' ;", null);
        cu.moveToPosition(row);
        String result = cu.getString(field);
        return result;
    }// end Display()

    public String DisplayAll(int row, int field){
        Cursor cu = db.rawQuery("select * from "+ dbhelper.getTableName() +" ;", null);
        cu.moveToPosition(row);
        String result = cu.getString(field);
        return result;
    }// end Display()

    public void insert(Object_News ob){
        ContentValues cv = new ContentValues();
        cv.put(SID, ob.getOid());
        cv.put(TITLE,ob.getOTile());
        cv.put(CONTEXT,ob.getOMatn());
        cv.put(DATE,ob.getODate());
        cv.put(IMAGEURL,ob.getOImageUrl());
        cv.put(FAVORITE,ob.getOFav());
        cv.put(FACTION,ob.getOFav());
        db.insert(dbhelper.getTableName(), SID, cv);
        Log.i(TAG, "insert");
    }

    public String SelectSearch(int row, int field, String query){
        Cursor cu = db.rawQuery(query +" ;", null);
        cu.moveToPosition(row);
        String result = cu.getString(field);
        return result;
    }// end Display()

    public String ShowInGroup(String Group,int row, int field){
        Cursor cu = db.rawQuery("select * from " + dbhelper.getTableName() + "  GROUP BY " + Group + " ;", null);
        cu.moveToPosition(row);
        String result = cu.getString(field);
        return result;
    }

    public void	update(String WColumn, String WIndex, String Column, String Index){
        db.execSQL("update " + dbhelper.getTableName() + " set " + WColumn + " = '" + WIndex + "' where " + Column + " = '" + Index + "' ;");
        Log.i(TAG, "update");
    }












    /*public int calll(){
        Cursor cu = db.rawQuery("select * from "+ dbhelper.getTableName() +";", null);
        int s = cu.getCount();
        Log.i(TAG, "Count is: "+s);
        return s;
    }

    public String alll(int row, int field){
        Cursor cu = db.rawQuery("select * from "+ dbhelper.getTableName() +";", null);
        cu.moveToPosition(row);
        String result = cu.getString(field);
        return result;
    }// end Display()*/


}// end class
