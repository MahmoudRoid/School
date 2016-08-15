package ir.elegam.school.Helper;

public class Object_Schdual {
    public String Oid;
    public String OName;
    public String ODate;
    public String OTeacher;

    public Object_Schdual(String Oid,String OName,String ODate, String OTeacher){
        this.Oid = Oid;
        this.OName = OName;
        this.ODate = ODate;
        this.OTeacher = OTeacher;
    }

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }

    public String getOName() {
        return OName;
    }

    public void setOName(String OName) {
        this.OName = OName;
    }

    public String getODate() {
        return ODate;
    }

    public void setODate(String ODate) {
        this.ODate = ODate;
    }

    public String getOTeacher() {
        return OTeacher;
    }

    public void setOTeacher(String OTeacher) {
        this.OTeacher = OTeacher;
    }
}
