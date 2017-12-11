package in.adityaparmar.server;

import in.adityaparmar.server.entity.User;
import in.adityaparmar.server.entity.request.Folder;
import in.adityaparmar.server.entity.response.ContentLoadResponse;
import in.adityaparmar.server.entity.response.Response;
import in.adityaparmar.server.entity.response.SignInResponse;
import in.adityaparmar.server.repository.UserRepository;
import in.adityaparmar.server.service.ContentService;
import in.adityaparmar.server.service.MappingService;
import in.adityaparmar.server.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
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

    @Autowired
    private MappingService mappingService;


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
        mockuser.setEmail("rajpatel@gmail.com");
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

        ContentLoadResponse response = contentService.CreateFolder(folder);

        assertThat(response.getResponse().getStatus()).isEqualTo("success");


    }

    @Test
    public void UpdateProfile() throws Exception {

        User mockuser = new User();
        mockuser.setId(5);
        mockuser.setFirstname("Raj");
        mockuser.setLastname("Par");
        mockuser.setEmail("raj@gmail.com");
        mockuser.setPassword("1234");
        mockuser.setInterests("cricket");
        mockuser.setAboutme("coder");

        SignInResponse response = userService.Update(mockuser);

        assertThat(response.getResponse().getStatus()).isEqualTo("success");


    }

    @Test
    public void UploadFile() throws Exception {



        ContentLoadResponse response = contentService.UploadFile("dropbox.zip","Mon Dec 11 02:26:13 PST 2017_dropbox.zip",1,1);

        assertThat(response.getResponse().getStatus()).isEqualTo("success");


    }

    @Test
    public void LoadFolder() throws Exception {


        Folder folder = new Folder();
        folder.setContentid(1);
        folder.setUserid(1);
        folder.setFoldername("Adi");

        ContentLoadResponse response = contentService.getFolderData(folder);

        assertThat(response.getResponse().getStatus()).isEqualTo("success");


    }

    @Test
    public void LeaveGroup() throws Exception {


        Folder folder = new Folder();
        folder.setContentid(1);
        folder.setUserid(1);
        folder.setFoldername("Adi");

        Response response = mappingService.RemoveMember(1,1);

        assertThat(response.getStatus()).isEqualTo("success");


    }



}
