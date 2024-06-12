package com.devsu.accountback.controller;

import com.devsu.accountback.entity.Movement;
import com.devsu.accountback.service.MovementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movimientos")
public class MovementController {
    private final MovementService service;

    public MovementController(MovementService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Movement> create(@Valid @RequestBody Movement movement) {
        Movement savedMovement = service.create(movement);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMovement);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movement> getById(@PathVariable("id") Long movementId) {
        Movement movement = service.getById(movementId);
        return ResponseEntity.ok(movement);
    }

    @GetMapping
    public ResponseEntity<List<Movement>> getAll() {
        List<Movement> movements = service.getAll();
        return ResponseEntity.ok(movements);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long movementId) {
        service.delete(movementId);
        return ResponseEntity.noContent().build();
    }
}
