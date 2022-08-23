package com.clinic.dentalclinic.dto;

import com.clinic.dentalclinic.model.Dentist;
import com.clinic.dentalclinic.model.Patient;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AppointmentDto {
    private Long id;

    private Long patientId;

    private Long dentistId;

    private String date;
}
