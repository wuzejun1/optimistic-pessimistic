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
import org.wonderbeat.home.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@ActiveProfiles("dev")
public class TransferTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void update() throws Exception {
        userService.createUser(1234);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/money/deposit").contentType(MediaType
                .APPLICATION_FORM_URLENCODED).param("userId", "1234").param("amount", "1000"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    public void negativeBalanceUpdate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/balance/deposit").contentType(MediaType
                .APPLICATION_FORM_URLENCODED).param("userId", "123").param("amount", "-1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void transfer() throws Exception {
        userService.createUser(123);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/money/deposit").contentType(MediaType
                .APPLICATION_FORM_URLENCODED).param("userId", "123").param("amount", "1000"))
                .andExpect(MockMvcResultMatchers.status().isFound());
        this.mockMvc.perform(MockMvcRequestBuilders.post("/money/transfer").contentType(MediaType
                .APPLICATION_FORM_URLENCODED).param("from", "123").param("to", "1234").param("amount", "1"))
                .andExpect(MockMvcResultMatchers.status().isFound());
    }

    @Test
    public void negativeAmountTransfer() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/money/transfer").contentType(MediaType
                .APPLICATION_FORM_URLENCODED).param("from", "123").param("to", "1234").param("amount", "-1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void invalidIdTransfer() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/money/transfer").contentType(MediaType
                .APPLICATION_FORM_URLENCODED).param("from", "rainbow").param("to", "unicorns").param("amount", "1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

}
