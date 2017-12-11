package in.adityaparmar.server.repository;

import in.adityaparmar.server.entity.Activity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActivityRepository extends CrudRepository<Activity, Integer> {

    List<Activity> findAllByUserid(int id);
}
