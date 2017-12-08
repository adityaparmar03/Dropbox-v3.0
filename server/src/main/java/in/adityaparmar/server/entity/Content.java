package in.adityaparmar.server.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Content {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int contentid;
    private String originalname;
    private String virtualname;
    private String date;
    private String type;
    private String star;
    private int userid;

    public Content() {
    }

    public Content(int contentid, String originalname, String virtualname, String date, String type, String star, int userid) {
        this.contentid = contentid;
        this.originalname = originalname;
        this.virtualname = virtualname;
        this.date = date;
        this.type = type;
        this.star = star;
        this.userid = userid;
    }

    public int getContentid() {
        return contentid;
    }

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    public String getOriginalname() {
        return originalname;
    }

    public void setOriginalname(String originalname) {
        this.originalname = originalname;
    }

    public String getVirtualname() {
        return virtualname;
    }

    public void setVirtualname(String virtualname) {
        this.virtualname = virtualname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
