package in.adityaparmar.server.service;

import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.response.Response;
import in.adityaparmar.server.entity.response.RootResponse;
import in.adityaparmar.server.repository.MappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MappingService {

    @Autowired
    private MappingRepository mappingRepository;

    public Response RemoveMember(int contentid, int userid){


        Response response = new Response();
        try{


            mappingRepository.deleteByContentidAndUserid(contentid,userid);
            response.setStatus("success");
            response.setMsg("Leave Group Successfully.");


        }
        catch (Exception e){

            response.setStatus("error");
            response.setMsg("Something went wrong."+e);

        }
        return response;
    }
}
