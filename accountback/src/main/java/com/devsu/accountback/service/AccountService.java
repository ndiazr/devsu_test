package com.devsu.accountback.service;

import com.devsu.accountback.entity.Account;
import com.devsu.accountback.exception.ResourceNotFoundException;
import com.devsu.accountback.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account create(Account account) {
        account.setBalance(account.getInitialBalance());
        return repository.save(account);
    }

    public Account update(Account account) {
        Optional<Account> existingAccount = repository.findById(account.getId());
        if (existingAccount.isPresent()) {
            Account accountObj = existingAccount.get();
            accountObj.setNumber(account.getNumber());
            accountObj.setType(account.getType());
            accountObj.setInitialBalance(account.getInitialBalance());
            accountObj.setState(account.getState());
            accountObj.setClientId(account.getClientId());
            return repository.save(accountObj);
        } else {
            throw new ResourceNotFoundException(String.format("Account with id %x not found", account.getId()));
        }
    }

    public Account getById(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Account with id %x not found", accountId)));
    }

    public List<Account> getAll() {
        return repository.findAll();
    }

    public void delete(Long accountId) {
        repository.deleteById(accountId);
    }

    public List<Map<String, Object>> accountReport(LocalDate startDate, LocalDate endDate, Long client) {
        return repository.findAccountByClient(client, startDate, endDate);
    }
}
