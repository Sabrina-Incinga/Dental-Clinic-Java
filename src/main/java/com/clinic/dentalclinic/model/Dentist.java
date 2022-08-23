package com.clinic.dentalclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Dentist {
    @Id
    @SequenceGenerator(name = "dentist_sequence",sequenceName = "dentist_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "dentist_sequence")
    private Long id;

    @Column(name= "FIRST_NAME", nullable = false, length = 200)
    private String firstName;

    @Column(name="LAST_NAME",nullable = false, length = 200)
    private String lastName;

    @Column(name = "MEDICAL_LICENCE_NUMBER", nullable = false, length = 100)
    private String medicalLicenceNumber;

    @OneToMany(mappedBy = "dentist", fetch = FetchType.EAGER)
    private Set<Appointment> appointments= new HashSet<>();
}
