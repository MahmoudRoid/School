package ir.elegam.school.Database.orm;

import com.orm.SugarRecord;

public class tbl_Videos extends SugarRecord {

    int id;
    String title,thumbnail,video_url;


    public tbl_Videos(){}

    public tbl_Videos(int id, String title, String thumbnail, String video_url){
        this.id=id;
        this.title=title;
        this.thumbnail=thumbnail;
        this.video_url=video_url;
    }

    public int getid() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
