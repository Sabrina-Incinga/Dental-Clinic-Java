package com.clinic.dentalclinic.dto;

import com.clinic.dentalclinic.model.Address;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class PatientDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private int identityCardNumber;

    private String admissionDate;

    private Address address;

    private Set<AppointmentDto> appointments;
}
