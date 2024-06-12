package com.devsu.accountback.service;

import com.devsu.accountback.entity.Account;
import com.devsu.accountback.entity.Movement;
import com.devsu.accountback.enums.MovementTypes;
import com.devsu.accountback.exception.ResourceNotFoundException;
import com.devsu.accountback.repository.MovementRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MovementService {
    private final MovementRepository repository;
    private final AccountService accountService;

    public MovementService(MovementRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }

    public Movement create(Movement movement) {
        Account account = accountService.getById(movement.getAccountId());
        double currentBalance = account.getBalance();
        double newBalance = 0;

        MovementTypes movementType = MovementTypes.valueOf(movement.getType());

        switch (movementType) {
            case RETIRO -> {
                if (currentBalance - movement.getAmount() < 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Saldo no disponible");
                }
                newBalance = currentBalance - movement.getAmount();
            }
            case DEPOSITO -> newBalance = currentBalance + movement.getAmount();
        }

        account.setBalance(newBalance);
        accountService.update(account);

        movement.setBalance(newBalance);
        return repository.save(movement);
    }

    public Movement getById(Long movementId) {
        return repository.findById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Movement with id %x not found", movementId)));
    }

    public List<Movement> getAll() {
        return repository.findAll();
    }

    public void delete(Long movementId) {
        repository.deleteById(movementId);
    }
}
