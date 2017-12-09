package in.adityaparmar.server.entity.response;

import in.adityaparmar.server.entity.Content;

import java.util.List;

public class ContentLoadResponse {

    List<Content> contents;
    Response response;
    int parentfolderid;

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public int getParentfolderid() {
        return parentfolderid;
    }

    public void setParentfolderid(int parentfolderid) {
        this.parentfolderid = parentfolderid;
    }
}
