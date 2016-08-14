package ir.elegam.school.Helper;

/**
 * Created by Droid on 8/14/2016.
 */
public class ImageCategoryGallery {
    public int id;
    public CharSequence title;

    public ImageCategoryGallery(int id, CharSequence title){
        this.id=id;
        this.title=title;
    }

    public int getid(){return this.id;}
    public CharSequence getTitle(){return this.title;}
}
