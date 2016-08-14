package ir.elegam.school.Helper;

/**
 * Created by Droid on 8/14/2016.
 */
public class ImagesDetailGallery {

    public int id;
    public String image_url;

    public ImagesDetailGallery(int id,String image_url){
        this.id=id;
        this.image_url=image_url;
    }

    public int getid(){return this.id;}
    public String getImage_url(){return this.image_url;}
}
