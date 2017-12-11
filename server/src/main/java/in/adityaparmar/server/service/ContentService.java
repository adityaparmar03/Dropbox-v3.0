package in.adityaparmar.server.service;

import in.adityaparmar.server.entity.Activity;
import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.entity.Mapping;
import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.request.Delete;
import in.adityaparmar.server.entity.request.Folder;
import in.adityaparmar.server.entity.request.Share;
import in.adityaparmar.server.entity.response.*;
import in.adityaparmar.server.repository.ActivityRepository;
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
    @Autowired
    private ActivityRepository activityRepository;

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
            String msg = name +" File Successfully uploaded.";
            response.setStatus("success");
            response.setMsg(msg);
            contentLoadResponse.setResponse(response);
            contentLoadResponse.setParentfolderid(parentfolderid);


            activityRepository.save(new Activity(msg,date.toString(),userid));


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
            String msg = folder.getFoldername()+" Folder Successfully Created.";
            response.setStatus("success");
            response.setMsg("Folder Successfully Created.");
            contentLoadResponse.setResponse(response);
            contentLoadResponse.setParentfolderid(folder.getContentid());

            activityRepository.save(new Activity(msg,date.toString(),folder.getUserid()));

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

            for(User user : users){

                Mapping mapping = new Mapping();
                mapping.setContentid(content.getContentid());

                Content rootdata =  contentRepository.findAllByUseridAndOriginalname(user.getId(),"root");
                mapping.setFolderid(rootdata.getContentid());

                mapping.setUserid(user.getId());
                mappingRepository.save(mapping);

                response.setStatus("success");
                String msg = content.getOriginalname()+" "+content.getType()+" Successfully shared with "+user.getFirstname()+" "+user.getLastname();
                response.setMsg(msg);
                activityRepository.save(new Activity(msg,new Date().toString(),sharedata.getUserid()));
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
               String msg = data.getContent().getOriginalname()+" "+data.getContent().getType()+" is successfully deleted.";
               response.setMsg(msg);
               activityRepository.save(new Activity(msg,new Date().toString(),data.getUserid()));
           }
           else{
               response.setStatus("error");
               response.setMsg("You are not authorize to perform this Task.");
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

    public Response dostar(Contents data){

        Response response = new Response();
        try{

            Content content = new Content();
            content.setContentid(data.getContentid());
            content.setOriginalname(data.getOriginalname());
            content.setVirtualname(data.getVirtualname());
            System.out.println(data.getStar());
            if(data.getStar().equals("NO"))
                content.setStar("YES");
            else
                content.setStar("NO");
            System.out.println(content.getStar());
            content.setDate(data.getDate());
            content.setUserid(data.getUserid());
            content.setType(data.getType());


            contentRepository.save(content);

            response.setStatus("success");
            response.setMsg("");



        }
        catch (Exception e){
            response.setStatus("error");
            response.setMsg("Something went wrong, Try Again.");


        }
        return response;
    }


}
