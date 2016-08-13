package ir.elegam.school.Helper;

public class Object_News {

    public String Oid;
    public String OTile;
    public String OMatn;
    public String ODate;
    public String OImageUrl;
    public String OFav;

    public Object_News(
            String Oid,
            String OTile,
            String OMatn,
            String ODate,
            String OImageUrl,
            String OFav){
        this.Oid = Oid;
        this.OTile = OTile;
        this.OMatn = OMatn;
        this.ODate = ODate;
        this.OImageUrl = OImageUrl;
        this.OFav = OFav;
    }

    public String getOid() {
        return Oid;
    }

    public void setOid(String oid) {
        Oid = oid;
    }

    public String getOTile() {
        return OTile;
    }

    public void setOTile(String OTile) {
        this.OTile = OTile;
    }

    public String getODate() {
        return ODate;
    }

    public void setODate(String ODate) {
        this.ODate = ODate;
    }

    public String getOImageUrl() {
        return OImageUrl;
    }

    public void setOImageUrl(String OImageUrl) {
        this.OImageUrl = OImageUrl;
    }

    public String getOMatn() {
        return OMatn;
    }

    public void setOMatn(String OMatn) {
        this.OMatn = OMatn;
    }

    public String getOFav() {
        return OFav;
    }

    public void setOFav(String OFav) {
        this.OFav = OFav;
    }
}// end class
