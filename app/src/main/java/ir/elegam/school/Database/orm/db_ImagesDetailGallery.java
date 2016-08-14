package ir.elegam.school.Database.orm;

import com.orm.SugarRecord;

/**
 * Created by Droid on 8/14/2016.
 */
public class db_ImagesDetailGallery extends SugarRecord{
    public int id;
    public String image_url;

    public db_ImagesDetailGallery(){}

    public db_ImagesDetailGallery(int id,String image_url){
        this.id=id;
        this.image_url=image_url;
    }

    public int getid(){return this.id;}
    public String getImage_url(){return this.image_url;}
}
