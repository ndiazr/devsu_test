package com.devsu.clientback.controller;

import com.devsu.clientback.entity.Client;
import com.devsu.clientback.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clientes")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Client> create(@Valid @RequestBody Client client) {
        Client savedClient = service.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getById(@PathVariable("id") Long clientId) {
        Client client = service.getById(clientId);
        return ResponseEntity.ok(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        List<Client> clients = service.getAll();
        return ResponseEntity.ok(clients);
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> update(@PathVariable("id") Long clientId, @Valid @RequestBody Client client) {
        client.setId(clientId);
        Client updatedClient = service.update(client);
        return ResponseEntity.ok(updatedClient);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long clientId) {
        service.delete(clientId);
        return ResponseEntity.noContent().build();
    }
}
