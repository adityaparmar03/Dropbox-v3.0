package in.adityaparmar.server.entity.response;

import in.adityaparmar.server.entity.User;

import java.util.List;

public class SuggestionResponse {

    List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
