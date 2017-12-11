package in.adityaparmar.server.entity.response;

import in.adityaparmar.server.entity.User;

import java.util.List;

public class Contents {
    private int contentid;
    private String originalname;
    private String virtualname;
    private String date;
    private String type;
    private String star;
    private int userid;
    private List<User> members;

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

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
