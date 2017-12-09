package in.adityaparmar.server.repository;

import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.Mapping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MappingRepository  extends CrudRepository<Mapping,Integer> {

        List<Mapping> findMappingByFolderidAndUserid(int folderid, int userid);
}
