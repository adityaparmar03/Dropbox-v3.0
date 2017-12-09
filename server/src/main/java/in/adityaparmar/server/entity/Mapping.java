package in.adityaparmar.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mapping {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer mappingid;

    private Integer folderid;

    private Integer contentid;

    private Integer userid;

    private Integer groupid;


    public Integer getMappingid() {
        return mappingid;
    }

    public void setMappingid(Integer mappingid) {
        this.mappingid = mappingid;
    }

    public Integer getFolderid() {
        return folderid;
    }

    public void setFolderid(Integer folderid) {
        this.folderid = folderid;
    }

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }
}
