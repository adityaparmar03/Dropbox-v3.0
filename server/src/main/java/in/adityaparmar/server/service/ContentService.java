package in.adityaparmar.server.service;

import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.Mapping;
import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.request.Folder;
import in.adityaparmar.server.entity.response.ContentLoadResponse;
import in.adityaparmar.server.entity.response.Response;
import in.adityaparmar.server.entity.response.RootResponse;
import in.adityaparmar.server.repository.ContentRepository;
import in.adityaparmar.server.repository.MappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.ConstantCallSite;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MappingRepository mappingRepository;


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



            // Get content

            List<Mapping> mapping = mappingRepository.findMappingByFolderidAndUserid(folder.getContentid(),folder.getUserid());

            List<Integer> contentid = mapping.stream().map(mapping1 -> mapping1.getContentid()).collect(Collectors.toList());

            contentRepository.findAllByContentidIn(contentid);

            contentLoadResponse.setContents(contentRepository.findAllByContentidIn(contentid));
            response.setStatus("success");
            response.setMsg("");
            contentLoadResponse.setResponse(response);
            contentLoadResponse.setParentfolderid(folder.getContentid());
       }
       catch (Exception e){

           response.setStatus("error");
           response.setMsg("Something went wrong");
           contentLoadResponse.setResponse(response);
           contentLoadResponse.setContents(null);
       }


        return contentLoadResponse;
    }

    public ContentLoadResponse UploadFile(String name, String path, int parentfolderid, int userid ){
        Response response = new Response();
        ContentLoadResponse contentLoadResponse = new ContentLoadResponse();
        Mapping mapping = new Mapping();

        try{

           // Add content start
            Date date = new Date();
            Content content = new Content();
            content.setOriginalname(name);
            content.setVirtualname(path);
            content.setStar("NO");
            content.setDate(date.toString());
            content.setUserid(userid);
            content.setType("file");

            content = contentRepository.save(content);

            // End

            // Mapping Start

            mapping.setContentid(content.getContentid());
            mapping.setFolderid(parentfolderid);
            mapping.setUserid(userid);
            mappingRepository.save(mapping);

            // Mapping End


            List<Mapping> mapping2 = mappingRepository.findMappingByFolderidAndUserid(parentfolderid,userid);

            List<Integer> contentid = mapping2.stream().map(mapping1 -> mapping1.getContentid()).collect(Collectors.toList());

            contentRepository.findAllByContentidIn(contentid);

            contentLoadResponse.setContents(contentRepository.findAllByContentidIn(contentid));
            response.setStatus("success");
            response.setMsg("File Successfully uploaded.");
            contentLoadResponse.setResponse(response);
            contentLoadResponse.setParentfolderid(parentfolderid);

        }
        catch (Exception e){

            response.setStatus("error");
            response.setMsg("Error in uploading, Please Try Again.");
            contentLoadResponse.setResponse(response);
            contentLoadResponse.setContents(null);

        }


        return contentLoadResponse;
    }

    public ContentLoadResponse CreateFolder(Folder folder ){

        Response response = new Response();
        ContentLoadResponse contentLoadResponse = new ContentLoadResponse();
        Mapping mapping = new Mapping();

        try{

            // Add content start
            Date date = new Date();
            Content content = new Content();
            content.setOriginalname(folder.getFoldername());
            content.setVirtualname(folder.getFoldername());
            content.setStar("NO");
            content.setDate(date.toString());
            content.setUserid(folder.getUserid());
            content.setType("folder");

            content = contentRepository.save(content);

            // End

            // Mapping Start

            mapping.setContentid(content.getContentid());
            mapping.setFolderid(folder.getContentid());
            mapping.setUserid(folder.getUserid());
            mappingRepository.save(mapping);

            // Mapping End


            List<Mapping> mapping2 = mappingRepository.findMappingByFolderidAndUserid(folder.getContentid(),folder.getUserid());

            List<Integer> contentid = mapping2.stream().map(mapping1 -> mapping1.getContentid()).collect(Collectors.toList());

            contentRepository.findAllByContentidIn(contentid);

            contentLoadResponse.setContents(contentRepository.findAllByContentidIn(contentid));
            response.setStatus("success");
            response.setMsg("Folder Successfully Created.");
            contentLoadResponse.setResponse(response);
            contentLoadResponse.setParentfolderid(folder.getContentid());

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
