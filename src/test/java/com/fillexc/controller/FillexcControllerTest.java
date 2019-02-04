package com.fillexc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fillexc.api.controller.FillexcController;
import com.fillexc.domain.Client;
import com.fillexc.domain.Email;
import com.fillexc.domain.SocialName;
import com.fillexc.service.FillexcService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("local")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FillexcControllerTest {

    @Mock
    private FillexcService fillexcService;

    @InjectMocks
    private FillexcController fillexcController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(fillexcController).build();
    }

    @Test
    public void testGetAllSocialName() throws Exception {
        List<SocialName> socialNames = Arrays.asList(
                new SocialName(1, "Ocupacional"),
                new SocialName(2, "MN"),
                new SocialName(3, "Assiste")
        );

        when(fillexcService.getAllSocialNames()).thenReturn(new ResponseEntity(socialNames, HttpStatus.OK));

        mockMvc.perform(get(FillexcController.API_PREFIX + "/socialname"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void testGetOcupacionalSocialName() throws Exception {
        final int id = 1;

        SocialName socialName = new SocialName(1, "Ocupacional");

        when(fillexcService.getSocialName(id)).thenReturn(new ResponseEntity(socialName, HttpStatus.OK));

        mockMvc.perform(get(FillexcController.API_PREFIX + "/socialname/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Ocupacional")));
    }

    @Test
    public void testGetAllClients() throws Exception {
        SocialName provider = new SocialName(1, "Ocupacional");

        List<Email> emails = Arrays.asList(
                new Email(1, "adm.braunas@gmail.com")
        );

        List<Client> clients = Arrays.asList(
                new Client(1, "Adalberto Cardoso", provider, emails),
                new Client(2, "Bobs", provider, new ArrayList<Email>()),
                new Client(3, "Leitura", provider, new ArrayList<Email>())
        );

        when(fillexcService.getAllClients()).thenReturn(new ResponseEntity(clients, HttpStatus.OK));

        mockMvc.perform(get(FillexcController.API_PREFIX + "/client"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", equalTo("Adalberto Cardoso")))
                .andExpect(jsonPath("$[0].emails", hasSize(1)))
                .andExpect(jsonPath("$[0].provider.name", equalTo("Ocupacional")));
    }

    @Test
    public void testGetBobsClient() throws Exception {
        final int id = 1;

        SocialName provider = new SocialName(1, "Ocupacional");

        Client client = new Client(1, "Bobs", provider, new ArrayList<Email>());

        when(fillexcService.getClient(id)).thenReturn(new ResponseEntity(client, HttpStatus.OK));
    }

    @Test
    public void testAddSocialName() throws Exception {
        SocialName socialName = new SocialName();
        socialName.setId(1);
        socialName.setName("Nova Ocupacional");

        when(fillexcService.addSocialName(socialName)).thenReturn(new ResponseEntity(socialName, HttpStatus.CREATED));

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post(FillexcController.API_PREFIX + "/socialname/new")
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(socialName)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Nova Ocupacional")))
                .andExpect(jsonPath("$.id", equalTo(1)));
    }
}