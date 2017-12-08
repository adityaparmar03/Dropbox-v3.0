package in.adityaparmar.server.entity.response;

import in.adityaparmar.server.entity.User;

public class SignInResponse {
    Response response;
    User users;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }
}
