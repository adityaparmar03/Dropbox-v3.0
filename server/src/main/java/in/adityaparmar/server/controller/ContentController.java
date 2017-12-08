package in.adityaparmar.server.controller;


import in.adityaparmar.server.entity.Content;
import in.adityaparmar.server.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/folder") // This means URL's start with /demo (after Application path)
public class ContentController  {

    @Autowired
    private ContentService contentService;



    @GetMapping(path="/load",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Iterable<Content> getAllUsers() {
        // This returns a JSON with the users
        return contentService.getFolderData();
    }


}