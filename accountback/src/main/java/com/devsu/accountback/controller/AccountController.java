package com.devsu.accountback.controller;

import com.devsu.accountback.entity.Account;
import com.devsu.accountback.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cuentas")
public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody Account account) {
        Account savedAccount = service.create(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> getById(@PathVariable("id") Long accountId) {
        Account account = service.getById(accountId);
        return ResponseEntity.ok(account);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        List<Account> accounts = service.getAll();
        return ResponseEntity.ok(accounts);
    }

    @PutMapping("{id}")
    public ResponseEntity<Account> update(@PathVariable("id") Long accountId, @Valid @RequestBody Account account) {
        account.setId(accountId);
        Account updatedAccount = service.update(account);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long accountId) {
        service.delete(accountId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("reportes")
    public ResponseEntity<List<Map<String, Object>>> accountsReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("client") Long client) {
        return ResponseEntity.ok(service.accountReport(startDate, endDate, client));
    }
}
