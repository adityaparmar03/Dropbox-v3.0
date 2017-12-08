package in.adityaparmar.dropbox.repository;

import in.adityaparmar.dropbox.entity.Test_Entity;
import in.adityaparmar.dropbox.entity.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Users_Repo extends MongoRepository<Users,String> {

    public Users findOneByEmail(String email);

    public Users findOneByEmailAndPassword(String email,String Password);

}
