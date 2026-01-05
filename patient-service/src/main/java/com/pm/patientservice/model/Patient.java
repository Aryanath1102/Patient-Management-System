package com.pm.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Email
    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private LocalDate registeredDate;

}
