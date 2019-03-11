package com.fillexc.api.controller;

import com.fillexc.domain.Client;
import com.fillexc.domain.SocialName;
import com.fillexc.service.FillexcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(FillexcController.API_PREFIX)
public class FillexcController {

    @Autowired
    private FillexcService fillexcService;

    public final static String API_PREFIX = "/api/fillexc";

    @GetMapping(value = "/client")
    public ResponseEntity<Page> getAllClients(@PageableDefault(sort = "name") Pageable page) {
        log.info("Getting all clients");
        return fillexcService.getAllClients(page);
    }

    @PostMapping(value = "/client/new")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        log.info(String.format("Saving new client '%s'", client.getName()));
        return fillexcService.addClient(client);
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable int id) {
        log.info("Getting client with id " + id);
        return fillexcService.getClient(id);
    }

    @GetMapping(value = "/socialname")
    public ResponseEntity<List> getAllSocialNames() {
        log.info("Getting all social names");
        return fillexcService.getAllSocialNames();
    }

    @PostMapping(value = "/socialname/new")
    public ResponseEntity<SocialName> addSocialName(@RequestBody SocialName socialName) {
        log.info(String.format("Saving new social name '%s'", socialName.getName()));
        return fillexcService.addSocialName(socialName);
    }

    @GetMapping(value = "/socialname/{id}")
    public ResponseEntity<SocialName> getSocialName(@PathVariable int id) {
        log.info("Getting social name with id " + id);
        return fillexcService.getSocialName(id);
    }
}
