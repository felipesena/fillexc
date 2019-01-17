package com.fillexc.service;

import com.fillexc.domain.Client;
import com.fillexc.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillexcService {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private S3Service s3Service;

    public ResponseEntity<Client> addClient(Client client) {
        return new ResponseEntity(clientRepo.save(client), HttpStatus.CREATED);
    }

    public ResponseEntity getAllClients() {
        List<Client> clients = clientRepo.findAll();

        if (clients.size() == 0)
            return new ResponseEntity(clients, HttpStatus.NO_CONTENT);

        return new ResponseEntity(clients, HttpStatus.OK);
    }
}
