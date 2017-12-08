package in.adityaparmar.dropbox.entity.Response;

import in.adityaparmar.dropbox.entity.Users;

public class SignInResponse {

    Response response;
    Users users;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
