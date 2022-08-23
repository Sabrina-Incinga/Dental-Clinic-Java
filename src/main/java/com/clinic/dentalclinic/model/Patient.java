package com.clinic.dentalclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Patient {

    @Id
    @SequenceGenerator(name = "patient_sequence",sequenceName = "patient_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "patient_sequence")
    private Long id;

    @Column(name= "FIRST_NAME", nullable = false, length = 200)
    private String firstName;

    @Column(name="LAST_NAME",nullable = false, length = 200)
    private String lastName;

    @Column(name="EMAIL", length = 250)
    private String email;

    @Column(name="IDENTITY_CARD_NUMBER", nullable = false)
    private int identityCardNumber;

    @Column(name="ADMISSION_DATE")
    private LocalDate admissionDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "id")
    private Address address;

    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    private Set<Appointment> appointments = new HashSet<>();

}
