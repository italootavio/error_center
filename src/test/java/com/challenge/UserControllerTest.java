package com.challenge;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@Sql("/test.sql")
@SpringBootTest(classes = ErrorCenter.class)
public class UserControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy filterChainProxy;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).addFilter(filterChainProxy).build();
    }

    private String obtainAccessToken(String username, String password) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", username);
        params.add("password", password);
        params.add("grant_type", "password");

        ResultActions result = mockMvc
                .perform(post("/oauth/token").params(params).with(httpBasic("codenation", "codenation123"))
                        .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
    @Test
    public void whenAddNewUser() throws Exception {
        String token = obtainAccessToken("admin", "admin");

        String userContent = "{ \"authority\":\"ROLE_BASIC\",\"username\": \"italo\",\"password\": 123}";
        MvcResult result = mockMvc.perform(post("/user")
                .header("Authorization", "Bearer " + token)
                .contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
                .content(userContent))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void whenAddNewUserWithRepeatedUsername() throws Exception {
        String token = obtainAccessToken("admin", "admin");
        //rever esse método junto com a colagem do UserController
        String userContent = "{ \"authority\":\"ROLE_BASIC\",\"username\": \"italo1\",\"password\": 123}";
        MvcResult result = mockMvc.perform(post("/user")
                .header("Authorization", "Bearer " + token)
                .contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
                .content(userContent))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(201, result.getResponse().getStatus());
    }

    @Test
    public void whenGetUsers() throws Exception {
        String token = obtainAccessToken("admin", "admin");

        MvcResult result = mockMvc.perform(get("/user")
                .header("Authorization", "Bearer " + token)
                .contentType("application/json;charset=UTF-8"))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void whenDeleteUserbyId() throws Exception {
        String token = obtainAccessToken("admin", "admin");
        String userContent = "{ \"authority\":\"ROLE_BASIC\",\"username\": \"italo1\",\"password\": 123}";
        MvcResult result = mockMvc.perform(post("/user")
                .header("Authorization", "Bearer " + token)
                .contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
                .content(userContent))
                .andReturn();
        result = mockMvc.perform(delete("/user/1")
                .header("Authorization", "Bearer " + token))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }

    @Test
    public void whenPutUserById() throws Exception {
        String token = obtainAccessToken("admin", "admin");
        String userContent = "{ \"authority\":\"ROLE_BASIC\",\"username\": \"italoTeste\",\"password\": 123}";
        MvcResult result = mockMvc.perform(post("/user")
                .header("Authorization", "Bearer " + token)
                .contentType(new MediaType(MediaType.APPLICATION_JSON.getType(),
                        MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8")))
                .content(userContent))
                .andReturn();
        userContent = "{ \"authority\":\"ROLE_BASIC\",\"username\": \"italoNovo\",\"password\": 123}";
        result = mockMvc.perform(put("/user/3")
                .header("Authorization", "Bearer " + token)
                .contentType("application/json;charset=UTF-8")
                .content(userContent))
                .andReturn();
        assertEquals(200, result.getResponse().getStatus());
    }
}