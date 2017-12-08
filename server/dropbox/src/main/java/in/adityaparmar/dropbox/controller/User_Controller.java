package in.adityaparmar.dropbox.controller;

import in.adityaparmar.dropbox.entity.Response.*;
import in.adityaparmar.dropbox.entity.Users;
import in.adityaparmar.dropbox.repository.Users_Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path="/user")
public class User_Controller {

    @Autowired
    private Users_Repo users_repo;

    //Models
    Response response = new Response();
    SignInResponse signInResponse = new SignInResponse();

    @RequestMapping(path="/signup",method = RequestMethod.POST)
    public Response SignUp(@RequestBody Users data){

        try{

            Users users =  users_repo.findOneByEmail(data.getEmail());
            data.setAll(data.getFirstname()+" "+data.getLastname()+" ("+data.getEmail()+")" );
            if(users==null){
                users_repo.save(data);
                response.setStatus("Success");
                response.setMsg("Account Created Successfully, Proceed to LogIn.");
                return response;
            }
            else{
                users_repo.save(data);
                response.setStatus("Error");
                response.setMsg("Account is already exist with email id: "+users.getEmail());
                return response;
            }


        }
        catch(Exception e){
            response.setStatus("error");
            response.setMsg("Something went wrong, Try Again.");
            return response;
        }

    }

    @RequestMapping(path="/signin",method = RequestMethod.POST)
    public SignInResponse SignIn(@RequestBody Users data){

        try{

            Users users =  users_repo.findOneByEmailAndPassword(data.getEmail(),data.getPassword());

            if(users==null){


                response.setStatus("Error");
                response.setMsg("Email / Password may wrong.");
                signInResponse.setResponse(response);
                return signInResponse;
            }
            else{

                response.setStatus("Success");
                response.setMsg("Successfully Logged In with "+users.getEmail());
                signInResponse.setUsers(users);
                signInResponse.setResponse(response);
                return signInResponse;
            }


        }
        catch(Exception e){
            response.setStatus("error");
            response.setMsg("Something went wrong, Try Again.");
            signInResponse.setResponse(response);
            return signInResponse;
        }

    }
}
