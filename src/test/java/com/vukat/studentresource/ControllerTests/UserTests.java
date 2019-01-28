package com.vukat.studentresource.ControllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vukat.studentresource.HiberApplicationTests;
import com.vukat.studentresource.domain.User;
import com.vukat.studentresource.payload.LoginRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
public class UserTests extends HiberApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    @Test
    public void userLogin() throws Exception {

        LoginRequest request = new LoginRequest();

        request.setUsername("okan@gmail.com");
        request.setPassword("123456");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void registerUser() throws Exception{

        User user = new User();

        user.setUsername("username");
        user.setPassword("123456789");
        user.setDepartment("CSE");
        user.setFullName("Joe Doe");
        user.setCreated_at(new Date());

        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user))).andExpect(status().isCreated());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




}
