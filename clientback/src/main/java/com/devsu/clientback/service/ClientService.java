package com.devsu.clientback.service;

import com.devsu.clientback.entity.Client;
import com.devsu.clientback.exception.ResourceNotFoundException;
import com.devsu.clientback.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public Client create(Client client) {
        return repository.save(client);
    }

    public Client update(Client client) {
        Optional<Client> existingClient = repository.findById(client.getId());
        if (existingClient.isPresent()) {
            Client clientObj = existingClient.get();
            clientObj.setName(client.getName());
            return repository.save(clientObj);
        } else {
            throw new ResourceNotFoundException(String.format("Client with id %x not found", client.getId()));
        }
    }

    public Client getById(Long clientId) {
        return repository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Client with id %x not found", clientId)));
    }

    public List<Client> getAll() {
        return repository.findAll();
    }

    public void delete(Long clientId) {
        repository.deleteById(clientId);
    }
}
