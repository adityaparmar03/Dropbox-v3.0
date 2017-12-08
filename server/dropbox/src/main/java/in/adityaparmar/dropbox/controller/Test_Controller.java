package in.adityaparmar.dropbox.controller;


import in.adityaparmar.dropbox.entity.Test_Entity;
import in.adityaparmar.dropbox.entity.Users;
import in.adityaparmar.dropbox.repository.Test_Repo;
import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/test")
public class Test_Controller {

    @Autowired
    private Test_Repo test_repo;

    @RequestMapping(path="/get")
    public List<Test_Entity> get(){
        return test_repo.findAll();

    }
    @RequestMapping(path="/getname")
    public List<Test_Entity> getname(){

        return test_repo.findOneByName("Aditya Parmar");

    }

    @RequestMapping(path="/add",method = RequestMethod.POST)
    public void add(@RequestBody Test_Entity data){
        test_repo.save(data);
    }

}
