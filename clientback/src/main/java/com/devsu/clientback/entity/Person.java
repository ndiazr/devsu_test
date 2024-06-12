package com.devsu.clientback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@MappedSuperclass
@Data
public class Person implements Serializable {
    private String dni;

    @Column(nullable = false)
    @NotBlank
    private String name;

    private String gender;

    private int age;

    @Column(nullable = false)
    @NotBlank
    private String address;

    @Column(nullable = false)
    @NotBlank
    private String phone;
}
