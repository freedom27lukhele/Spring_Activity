package com.example.demo.service;

import com.example.demo.dao.ClientRepository;
import com.example.demo.entity.Client;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository theClientRepositoryDao) {
        clientRepository = theClientRepositoryDao;
    }


    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(int theId) {
        Optional<Client> client = clientRepository.findById(theId);

        Client theClient = null;
        if (client.isPresent()) {
            theClient = client.get();

        } else {
            throw new RuntimeException("Did not find client with id: " + theId);
        }
        return theClient;
    }

    @Override
    @Transactional
    public Client save(Client theClient) {
        return clientRepository.save(theClient);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        clientRepository.deleteById(theId);
    }
}
