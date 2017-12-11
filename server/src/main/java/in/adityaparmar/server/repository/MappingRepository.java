package in.adityaparmar.server.repository;

import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.Mapping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MappingRepository  extends CrudRepository<Mapping,Integer> {

        List<Mapping> findMappingByFolderid(int folderid);

        List<Mapping> findAllByContentid(int contentid);

        @Transactional
        void deleteByContentidAndUserid(int contentid, int userid);
}
