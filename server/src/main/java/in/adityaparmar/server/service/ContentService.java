package in.adityaparmar.server.service;

import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.request.Folder;
import in.adityaparmar.server.entity.response.ContentLoadResponse;
import in.adityaparmar.server.entity.response.Response;
import in.adityaparmar.server.entity.response.RootResponse;
import in.adityaparmar.server.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.ConstantCallSite;
import java.util.Date;
import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;


    public RootResponse getRoot(User user){

        RootResponse rootResponse = new RootResponse();
        Response response = new Response();
        try{

            Content content =  contentRepository.findAllByUseridAndOriginalname(user.getId(),"root");
            rootResponse.setRootid(content.getContentid());
            response.setStatus("success");
            response.setMsg("");
            rootResponse.setResponse(response);

        }
        catch (Exception e){
            rootResponse.setRootid(0);
            response.setStatus("error");
            response.setMsg("Something went wrong.");
            rootResponse.setResponse(response);
        }
        return rootResponse;
    }
    public ContentLoadResponse getFolderData(Folder folder){


        Response response = new Response();
        ContentLoadResponse contentLoadResponse = new ContentLoadResponse();

        try{

           response.setStatus("success");
           response.setMsg("File uploaded successfully.");
           contentLoadResponse.setResponse(response);
           contentLoadResponse.setContents(contentRepository.findAllByUserid(folder.getUserid()));
       }
       catch (Exception e){

           response.setStatus("success");
           response.setMsg("File uploaded successfully.");
           contentLoadResponse.setResponse(response);
           contentLoadResponse.setContents(null);
       }


        return contentLoadResponse;
    }

    public ContentLoadResponse UploadFile(String name, String path, int parentfolderid, int userid ){
        Response response = new Response();
        ContentLoadResponse contentLoadResponse = new ContentLoadResponse();

        try{

            Date date = new Date();
            Content content = new Content();
            content.setOriginalname(name);
            content.setVirtualname(path);
            content.setStar("NO");
            content.setDate(date.toString());
            content.setUserid(userid);
            content.setType("file");

            contentRepository.save(content);

            response.setStatus("success");
            response.setMsg("File uploaded successfully.");
            contentLoadResponse.setResponse(response);
            contentLoadResponse.setContents(contentRepository.findAllByUserid(1));

        }
        catch (Exception e){

            response.setStatus("error");
            response.setMsg("Error in uploading, Please Try Again.");
            contentLoadResponse.setResponse(response);
            contentLoadResponse.setContents(null);

        }


        return contentLoadResponse;
    }
}
