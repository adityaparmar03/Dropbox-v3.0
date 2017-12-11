package in.adityaparmar.server.repository;



// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

import in.adityaparmar.server.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {



    User findUserByEmail(String email);
    User findUsersByEmailAndPassword(String email, String password);
    List<User> findUsersByEmailStartsWith(String keyword);
    List<User> findUsersByFirstnameStartsWith(String keyword);
}