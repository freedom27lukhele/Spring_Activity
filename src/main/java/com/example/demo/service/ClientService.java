package com.example.demo.service;

import com.example.demo.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client findById(int id);

    Client save(Client theClient);

    void deleteById(int id);
}
