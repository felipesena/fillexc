package com.fillexc.api.controller;

import com.fillexc.domain.Client;
import com.fillexc.service.FillexcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(FillexcController.API_PREFIX)
public class FillexcController {

    @Autowired
    private FillexcService fillexcService;

    public final static String API_PREFIX = "/api/fillexc";

    @GetMapping(value = "/client")
    private ResponseEntity<List> getAllClients() {
        log.info("Getting all clients");
        return fillexcService.getAllClients();
    }

    @PostMapping(value = "/client/new")
    private ResponseEntity<Client> addClient(@RequestBody Client client) {
        log.info(String.format("Saving new client '%s'", client.getName()));
        return fillexcService.addClient(client);
    }
}
