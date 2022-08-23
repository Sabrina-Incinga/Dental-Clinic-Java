package com.clinic.dentalclinic.dto;


import java.util.Set;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class DentistDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String medicalLicenceNumber;

    private Set<AppointmentDto> appointments;
}
