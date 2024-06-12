package com.devsu.accountback.entity;

import com.devsu.accountback.annotations.Options;
import com.devsu.accountback.enums.MovementTypes;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "movements")
@Data
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private LocalDate date;

    @NotNull
    @Options(enumClass = MovementTypes.class)
    @Column(nullable = false)
    private String type;

    @NotNull
    @Column(nullable = false)
    private double amount;

    @Column()
    private double balance;

    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    @JsonBackReference
    private Account account;

    @NotNull
    @Column(name = "account_id", nullable = false)
    private Long accountId;
}
