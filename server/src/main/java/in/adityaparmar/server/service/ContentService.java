package in.adityaparmar.server.service;

import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.Mapping;
import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.request.Delete;
import in.adityaparmar.server.entity.request.Folder;
import in.adityaparmar.server.entity.request.Share;
import in.adityaparmar.server.entity.response.ContentLoadResponse;
import in.adityaparmar.server.entity.response.Contents;
import in.adityaparmar.server.entity.response.Response;
import in.adityaparmar.server.entity.response.RootResponse;
import in.adityaparmar.server.repository.ContentRepository;
import in.adityaparmar.server.repository.MappingRepository;
import in.adityaparmar.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private MappingRepository mappingRepository;
    @Autowired
    private UserRepository userRepository;

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

            List<Mapping> mapping = mappingRepository.findMappingByFolderid(folder.getContentid());

            List<Integer> contentid = mapping.stream().map(mapping1 -> mapping1.getContentid()).collect(Collectors.toList());

            contentRepository.findAllByContentidIn(contentid);


            List<Content> contents = contentRepository.findAllByContentidIn(contentid);
            List<Contents> mixture = new ArrayList<>();
            for(Content content : contents){
                Contents mix = new Contents();
                mix.setContentid(content.getContentid());
                mix.setOriginalname(content.getOriginalname());
                mix.setVirtualname(content.getVirtualname());
                mix.setDate(content.getDate());
                mix.setStar(content.getStar());
                mix.setType(content.getType());
                mix.setUserid(content.getUserid());
                mix.setMembers(Members(content.getContentid()));

                mixture.add(mix);

            }
            contentLoadResponse.setContents(mixture);
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


            List<Mapping> mapping2 = mappingRepository.findMappingByFolderid(parentfolderid);

            List<Integer> contentid = mapping2.stream().map(mapping1 -> mapping1.getContentid()).collect(Collectors.toList());

            contentRepository.findAllByContentidIn(contentid);

            List<Content> contents = contentRepository.findAllByContentidIn(contentid);
            List<Contents> mixture = new ArrayList<>();
            for(Content icontent : contents){
                Contents mix = new Contents();
                mix.setContentid(icontent.getContentid());
                mix.setOriginalname(icontent.getOriginalname());
                mix.setVirtualname(icontent.getVirtualname());
                mix.setDate(icontent.getDate());
                mix.setStar(icontent.getStar());
                mix.setType(icontent.getType());
                mix.setUserid(icontent.getUserid());
                mix.setMembers(Members(icontent.getContentid()));

                mixture.add(mix);

            }
            contentLoadResponse.setContents(mixture);

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


            List<Mapping> mapping2 = mappingRepository.findMappingByFolderid(folder.getContentid());

            List<Integer> contentid = mapping2.stream().map(mapping1 -> mapping1.getContentid()).collect(Collectors.toList());

            contentRepository.findAllByContentidIn(contentid);

            List<Content> contents = contentRepository.findAllByContentidIn(contentid);
            List<Contents> mixture = new ArrayList<>();
            for(Content icontent : contents){
                Contents mix = new Contents();
                mix.setContentid(icontent.getContentid());
                mix.setOriginalname(icontent.getOriginalname());
                mix.setVirtualname(icontent.getVirtualname());
                mix.setDate(icontent.getDate());
                mix.setStar(icontent.getStar());
                mix.setType(icontent.getType());
                mix.setUserid(icontent.getUserid());
                mix.setMembers(Members(icontent.getContentid()));

                mixture.add(mix);

            }
            contentLoadResponse.setContents(mixture);

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

    public Response Share(Share sharedata ){

        Content content = sharedata.getContent();
        List<User> users = sharedata.getUsers();

        Response response = new Response();


        try{


            // End

            // Mapping Start
            for(User user : users){

                Mapping mapping = new Mapping();
                mapping.setContentid(content.getContentid());

                Content rootdata =  contentRepository.findAllByUseridAndOriginalname(user.getId(),"root");
                mapping.setFolderid(rootdata.getContentid());

                mapping.setUserid(user.getId());
                mappingRepository.save(mapping);

                response.setStatus("success");
                response.setMsg(content.getOriginalname()+" "+content.getType()+" Successfully Created.");
            }




        }
        catch (Exception e){

            response.setStatus("error");
            response.setMsg("Error in Sharing, Please Try Again.");

        }


        return response;
    }

    public Response DeleteFile(Delete data){



        Response response = new Response();


        try{

           if(data.getContent().getUserid() == data.getUserid()){
               contentRepository.deleteByContentid(data.getContent().getContentid());
               response.setStatus("Success");
               response.setMsg(data.getContent().getOriginalname()+" "+data.getContent().getType()+" is successfully deleted.");
           }
           else{
               response.setStatus("error");
               response.setMsg("You are not authorize to perform this operation.");
           }




        }
        catch (Exception e){

            response.setStatus("error");
            response.setMsg("Error in Deleting, Please Try Again.");

        }


        return response;
    }


    public List<User> Members(int id){



        List<Mapping> content = mappingRepository.findAllByContentid(id);
        List<Integer> userids = content.stream().map(content1 -> content1.getUserid()).collect(Collectors.toList());


        List<User> user = userRepository.findUsersByIdIn(userids);

        return user;

    }


}
