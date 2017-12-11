package in.adityaparmar.server;

import in.adityaparmar.server.entity.Activity;
import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.request.Folder;
import in.adityaparmar.server.entity.response.Response;
import in.adityaparmar.server.entity.response.SignInResponse;
import in.adityaparmar.server.repository.UserRepository;
import in.adityaparmar.server.service.ContentService;
import in.adityaparmar.server.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.Assertions.assertThat;


public class JunitTest extends ServerApplicationTests {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ContentService contentService;


    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void SignInRightCredentials() throws Exception {

        User mockuser = new User();
        mockuser.setEmail("parmar415@gmail.com");
        mockuser.setPassword("1234");


        SignInResponse signInResponse =  userService.SignIn(mockuser);


        assertThat(signInResponse.getResponse().getStatus()).isEqualTo("success");


    }

    @Test
    public void SignInWrongCredentials() throws Exception {

        User mockuser = new User();
        mockuser.setEmail("parmar415@gmail.com");
        mockuser.setPassword("123456");

        SignInResponse signInResponse =  userService.SignIn(mockuser);


        assertThat(signInResponse.getResponse().getStatus()).isEqualTo("error");


    }

    @Test
    public void SignUpNewUser() throws Exception {

        User mockuser = new User();
        mockuser.setFirstname("Raj");
        mockuser.setLastname("Par");
        mockuser.setEmail("raj@gmail.com");
        mockuser.setPassword("1234");


        Response response = userService.SignUp(mockuser);
        System.out.println(response.getMsg());
        assertThat(response.getStatus()).isEqualTo("success");


    }

    @Test
    public void SignUpAlradyExistUser() throws Exception {

        User mockuser = new User();
        mockuser.setFirstname("Adi");
        mockuser.setLastname("Par");
        mockuser.setEmail("parmar415@gmail.com");
        mockuser.setPassword("123456");


        Response response = userService.SignUp(mockuser);

        assertThat(response.getStatus()).isEqualTo("error");


    }


    @Test
    public void CheckSession() throws Exception {

        User mockuser = new User();
        mockuser.setId(1);


        SignInResponse response = userService.CheckSession(mockuser);

        assertThat(response.getResponse().getStatus()).isEqualTo("success");


    }

    @Test
    public void CreateFolder() throws Exception {

        Folder folder = new Folder();
        folder.setContentid(1);
        folder.setUserid(1);
        folder.setFoldername("Adi");

        SignInResponse response = contentService.

        assertThat(response.getResponse().getStatus()).isEqualTo("success");


    }

}
