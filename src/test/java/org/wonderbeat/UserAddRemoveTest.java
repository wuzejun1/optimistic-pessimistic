package org.wonderbeat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class UserAddRemoveTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void addUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/add").contentType(MediaType
                .APPLICATION_FORM_URLENCODED).param("userId", "123"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    public void removeUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/user/block").contentType(MediaType
                .APPLICATION_FORM_URLENCODED).param("userId", "123"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }
}
