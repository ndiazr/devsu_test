package com.devsu.accountback.entity;

import com.devsu.accountback.annotations.Options;
import com.devsu.accountback.enums.AccountTypes;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "accounts")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String number;

    @NotNull
    @Options(enumClass = AccountTypes.class)
    @Column(nullable = false)
    private String type;

    @Min(0)
    @Column(nullable = false)
    private double initialBalance;

    @Column(nullable = false)
    private double balance;

    @NotNull
    @Column(nullable = false)
    private Boolean state;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Movement> movements;

    @ManyToOne
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private Client client;

    @NotNull
    @Column(name = "client_id", nullable = false)
    private Long clientId;
}
