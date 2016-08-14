package ir.elegam.school.Classes;

/**
 * Created by Amoozesh on 7/18/2016.
 */
public class Article {
    String number,english_name,image_url,pdf_url;

    public Article(String number, String english_name, String image_url, String pdf_url){
        this.number=number;
        this.english_name=english_name;
        this.image_url=image_url;
        this.pdf_url=pdf_url;
    }

    public String getNumber(){return this.number;}
    public String getEnglish_name(){return this.english_name;}
    public String getImage_url(){return this.image_url;}
    public String getPdf_url(){return this.pdf_url;}
}
