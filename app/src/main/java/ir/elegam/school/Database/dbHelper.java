package ir.elegam.school.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import ir.elegam.school.Classes.Variables;

public class dbHelper extends SQLiteOpenHelper{
	
	private String TAG = Variables.Tag;
	private static final String DB_NAME 		= "DB_SCHOOl";
	private static final String TABLE_NAME_NEWS	= "tbl_news";
	private static final int DB_VERSION 		= 1;
	
	private static final String ID 			= "_id";
	private static final String Sid			= "Sid";
	private static final String Title 		= "Title";
	private static final String Context 	= "Context";
	private static final String Date 		= "Date";
	private static final String ImageUrl 	= "ImageUrl";
	private static final String Favorite 	= "Favorite";
	private static final String Faction 	= "Faction";
	
	private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME_NEWS +" ( "+
    ID       		+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
	Sid     		+" INTEGER, "+
	Title     		+" TEXT, "+
	Context     	+" TEXT, "+
	Date     		+" TEXT, "+
	ImageUrl     	+" TEXT, "+
	Faction			+" TEXT, "+
	Favorite    	+" TEXT "+
    ");";
	
	public dbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase mydb) {
		mydb.execSQL(CREATE_TABLE);
		Log.i(TAG, "Database Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase mydb, int oldVersion, int newVersion) {
		mydb.execSQL("DROP TABLE IF EXISTS '"+TABLE_NAME_NEWS+"' ;");
		onCreate(mydb);
		Log.i(TAG, "Database Upgraded");
	}
	
	public String getTableName(){
		return TABLE_NAME_NEWS;
	}
	
	public String getRowId(){
		return ID;
	}
	
}// end class
