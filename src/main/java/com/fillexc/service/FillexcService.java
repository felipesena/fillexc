package com.fillexc.service;

import com.fillexc.domain.Client;
import com.fillexc.domain.SocialName;
import com.fillexc.repository.ClientRepository;
import com.fillexc.repository.SocialNameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FillexcService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private SocialNameRepository socialNameRepo;

    public ResponseEntity<Client> addClient(Client client) {
        return new ResponseEntity(clientRepo.save(client), HttpStatus.CREATED);
    }

    public ResponseEntity getClient(int id) {
        Client client = clientRepo.getOne(id);

        if (client == null) {
            log.warn(String.format("Client with id %d not found", id));
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity(client, HttpStatus.OK);
    }

    public ResponseEntity getAllClients() {
        List<Client> clients = clientRepo.findAll();

        if (clients.size() == 0)
            return new ResponseEntity(clients, HttpStatus.NO_CONTENT);

        return new ResponseEntity(clients, HttpStatus.OK);
    }

    public ResponseEntity getAllSocialNames() {
        List<SocialName> socialNames = socialNameRepo.findAll();

        if (socialNames.size() == 0) {
            return new ResponseEntity(socialNames, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(socialNames, HttpStatus.OK);
    }

    public ResponseEntity<SocialName> addSocialName(SocialName socialName) {
        return new ResponseEntity(socialNameRepo.save(socialName), HttpStatus.CREATED);
    }

    public ResponseEntity getSocialName(int id) {
        SocialName socialName = socialNameRepo.getOne(id);

        if (socialName == null) {
            log.warn(String.format("Social name with id %d not found", id));
            return ResponseEntity.notFound().build();
        }

        return new ResponseEntity(socialName, HttpStatus.OK);
    }
}
