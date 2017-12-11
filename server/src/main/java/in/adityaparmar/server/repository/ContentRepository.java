package in.adityaparmar.server.repository;


import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.Mapping;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContentRepository extends CrudRepository<Content,Integer> {
    List<Content> findAllByUserid(int userid);

    Content findAllByUseridAndOriginalname(int userid, String root);

    List<Content> findAllByContentidIn( List<Integer> contentid);



}