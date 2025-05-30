package com.example.demo.controller;


import com.example.demo.entity.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientRestController {

    private ClientService clientService;

    @Autowired
    public ClientRestController(ClientService theClientService) {
        clientService = theClientService;
    }

    //getting all the clients
    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    //get specific client by id
    @GetMapping("/clients/{clientId}")
    public Client getClient(@PathVariable int clientId) {
        Client theClient = clientService.findById(clientId);

        if (theClient == null) {
            throw new RuntimeException("Client with id " + clientId + " not found");
        }
        return theClient;
    }

    @PostMapping("/clients")
    public Client addClient(@RequestBody Client theClient) {

        theClient.setId(0);

        Client client = clientService.save(theClient);

        return client;
    }

    @PutMapping("/clients")
    public Client updateClient(@RequestBody Client theClient) {
        Client dbclient = clientService.save(theClient);

        return dbclient;
    }

    @DeleteMapping("/clients/{clientId}")
    public void deleteClient(@PathVariable int clientId) {
        clientService.deleteById(clientId);
    }

}
