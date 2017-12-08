package in.adityaparmar.dropbox.repository;

import in.adityaparmar.dropbox.entity.Test_Entity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Test_Repo extends MongoRepository<Test_Entity,String> {

     public List<Test_Entity> findOneByName(String name);
}
