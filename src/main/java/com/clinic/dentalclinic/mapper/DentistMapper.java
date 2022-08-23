package com.clinic.dentalclinic.mapper;

import com.clinic.dentalclinic.dto.AppointmentDto;
import com.clinic.dentalclinic.dto.DentistDto;
import com.clinic.dentalclinic.model.Dentist;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DentistMapper {

    public Dentist dentistDtoToEntity(DentistDto dto){
        Dentist dentist = new Dentist();
        dentist.setFirstName(dto.getFirstName());
        dentist.setLastName(dto.getLastName());
        dentist.setMedicalLicenceNumber(dto.getMedicalLicenceNumber());

        return dentist;
    }


}
