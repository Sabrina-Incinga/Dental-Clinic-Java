package com.clinic.dentalclinic.mapper;

import com.clinic.dentalclinic.dto.AppointmentDto;
import com.clinic.dentalclinic.model.Appointment;
import com.clinic.dentalclinic.model.Dentist;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


@Component
public class AppointmentMapper {
    public LocalDateTime stringToLocalDateTime(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime date = LocalDateTime.parse(stringDate, formatter);
        return date;
    }

    public Appointment appointmentDtoToEntity(AppointmentDto dto){
        Appointment appointment = new Appointment();
        appointment.setDate(stringToLocalDateTime(dto.getDate()));

        return appointment;
    }

    public AppointmentDto appointmentEntityToDto(Appointment appointment){
        AppointmentDto dto = new AppointmentDto();
        dto.setId(appointment.getId());
        dto.setDate(appointment.getDate().toString());
        dto.setDentistId(appointment.getDentist().getId());
        dto.setPatientId(appointment.getPatient().getId());

        return dto;
    }

}
