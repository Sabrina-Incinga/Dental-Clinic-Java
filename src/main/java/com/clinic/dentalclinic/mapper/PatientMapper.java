package com.clinic.dentalclinic.mapper;

import com.clinic.dentalclinic.dto.AppointmentDto;
import com.clinic.dentalclinic.dto.PatientDto;
import com.clinic.dentalclinic.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PatientMapper {

    private final AppointmentMapper appointmentMapper;

    public LocalDate stringToLocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public Patient patientDtoToEntity(PatientDto dto){
        Patient patient = new Patient();
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setAddress(dto.getAddress());
        patient.setEmail(dto.getEmail());
        patient.setIdentityCardNumber(dto.getIdentityCardNumber());
        patient.setAdmissionDate(stringToLocalDate(dto.getAdmissionDate()));

        return patient;
    }

}
