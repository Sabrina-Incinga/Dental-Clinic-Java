package com.clinic.dentalclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @SequenceGenerator(name = "address_sequence",sequenceName = "address_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =  "address_sequence")
    private Long id;

    @Column(name="STREET", length = 200)
    private String street;
    @Column(name="NUMBER", length = 200)
    private short number;
    @Column(name="CITY", length = 200)
    private String city;
    @Column(name="PROVINCE", length = 200)
    private String province;

}
