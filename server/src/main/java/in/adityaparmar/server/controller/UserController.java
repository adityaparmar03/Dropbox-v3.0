package in.adityaparmar.server.controller;





import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.response.Response;
import in.adityaparmar.server.entity.response.SignInResponse;
import in.adityaparmar.server.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@RestController   // This means that this class is a Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired
    private UserService userService;



    @RequestMapping(path="/signup",method = RequestMethod.POST) // Map ONLY POST Requests
    public  Response SignUp (@RequestBody User user) {


        return  userService.SignUp(user);

    }

    @RequestMapping(path="/signin",method = RequestMethod.POST) // Map ONLY POST Requests
    public SignInResponse SignIn (@RequestBody User user) {


        return userService.SignIn(user);

    }





}