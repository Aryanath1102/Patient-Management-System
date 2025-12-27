package com.pm.patientservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
        name="patients",
        indexes = {
                @Index(name = "idx_patient_email", columnList = "email"),
                @Index(name = "idx_patient_name", columnList = "name")
        }
)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull
    @Email
    @Column(unique= true,nullable = false,length = 150)
    private String email;

    @NotNull
    @Size(min = 5, max = 255)
    @Column(nullable = false)
    private String address;

    @NotNull
    @Past
    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @PastOrPresent
    @Column(nullable = false)
    private LocalDate registeredDate;

}
