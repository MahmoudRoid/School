package ir.elegam.school.Database.orm;

import com.orm.SugarRecord;

/**
 * Created by Droid on 8/14/2016.
 */
public class db_ImageCategoryGallery extends SugarRecord {
    public int id;
    public CharSequence title;

    public db_ImageCategoryGallery(){}

    public db_ImageCategoryGallery(int id, CharSequence title){
        this.id=id;
        this.title=title;
    }

    public int getid(){return this.id;}
    public CharSequence getTitle(){return this.title;}
}
