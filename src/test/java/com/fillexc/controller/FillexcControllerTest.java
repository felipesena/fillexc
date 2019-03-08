package com.fillexc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fillexc.api.controller.FillexcController;
import com.fillexc.domain.SocialName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FillexcControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void givenNoAuthRequest_shouldFailWith401() throws Exception {
        mockMvc.perform(get(FillexcController.API_PREFIX + "/socialname"))
                .andExpect(status().is4xxClientError());
    }

    @WithMockUser("fsena")
    @Test
    public void testGetAllSocialName() throws Exception {

        mockMvc.perform(get(FillexcController.API_PREFIX + "/socialname"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @WithMockUser("fsena")
    @Test
    public void testGetOcupacionalSocialName() throws Exception {
        final int id = 1;

        mockMvc.perform(get(FillexcController.API_PREFIX + "/socialname/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Ocupacional")));
    }

    @WithMockUser("fsena")
    @Test
    public void testGetAllClients() throws Exception {

        mockMvc.perform(get(FillexcController.API_PREFIX + "/client"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Adalberto Cardoso")))
                .andExpect(jsonPath("$[0].emails", hasSize(1)))
                .andExpect(jsonPath("$[0].provider.name", equalTo("Ocupacional")));
    }

    @WithMockUser("fsena")
    @Test
    public void testAddSocialName() throws Exception {
        SocialName socialName = new SocialName();
        socialName.setName("Nova Ocupacional");

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post(FillexcController.API_PREFIX + "/socialname/new")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(socialName)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Nova Ocupacional")))
                .andExpect(jsonPath("$.id", equalTo(4)));
    }
}