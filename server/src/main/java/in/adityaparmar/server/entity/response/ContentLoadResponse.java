package in.adityaparmar.server.entity.response;

import in.adityaparmar.server.entity.Content;

import java.util.List;

public class ContentLoadResponse {

    List<Contents> contents;
    Response response;
    int parentfolderid;

    public List<Contents> getContents() {
        return contents;
    }

    public void setContents(List<Contents> contents) {
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
