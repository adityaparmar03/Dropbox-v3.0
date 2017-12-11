package in.adityaparmar.server.entity.request;

import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.response.Contents;

public class Delete {

    Contents content;
    int userid;

    public Contents getContent() {
        return content;
    }

    public void setContent(Contents content) {
        this.content = content;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
