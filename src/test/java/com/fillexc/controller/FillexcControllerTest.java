package com.fillexc.controller;

import com.fillexc.api.controller.FillexcController;
import com.fillexc.domain.SocialName;
import com.fillexc.service.FillexcService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FillexcControllerTest {

    @Autowired
    private FillexcService fillexcService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
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
}
