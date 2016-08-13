package ir.elegam.school.Classes;

/**
 * Created by Droid on 8/13/2016.
 */
public class SuperiorStudents {
    public String name,academic_year,class_number,image_url;

    public SuperiorStudents(String name,String academic_year,String class_number,String image_url){
        this.name=name;
        this.academic_year=academic_year;
        this.class_number=class_number;
        this.image_url=image_url;
    }


    public String getname(){return this.name;}
    public String getAcademic_year(){return  this.academic_year;}
    public String getClass_number(){return this.class_number;}
    public String  getImage_url(){return this.image_url;}
}
