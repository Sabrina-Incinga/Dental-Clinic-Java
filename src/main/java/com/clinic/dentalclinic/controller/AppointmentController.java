package com.clinic.dentalclinic.controller;

import com.clinic.dentalclinic.dto.AppointmentDto;
import com.clinic.dentalclinic.exceptions.BadRequestException;
import com.clinic.dentalclinic.exceptions.ResourceNotFoundException;
import com.clinic.dentalclinic.mapper.AppointmentMapper;
import com.clinic.dentalclinic.model.Appointment;
import com.clinic.dentalclinic.model.Dentist;
import com.clinic.dentalclinic.model.Patient;
import com.clinic.dentalclinic.service.AppointmentService;
import com.clinic.dentalclinic.service.DentistService;
import com.clinic.dentalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    private final AppointmentMapper mapper;

    private final PatientService patientService;

    private final DentistService dentistService;

    @PostMapping
    public ResponseEntity<AppointmentDto> createNewAppointment(@RequestBody AppointmentDto appointmentDto) throws BadRequestException {
        Patient patient = patientService.getPatientById(appointmentDto.getPatientId());
        Dentist dentist = dentistService.getDentistById(appointmentDto.getDentistId());

        if (patient != null && dentist != null) {
            Appointment appointmentToCreate = mapper.appointmentDtoToEntity(appointmentDto);
            appointmentToCreate.setDentist(dentist);
            appointmentToCreate.setPatient(patient);

            Appointment createdAppointment = appointmentService.createAppointment(appointmentToCreate);
            patient.getAppointments().add(createdAppointment);
            dentist.getAppointments().add(createdAppointment);


            final long id = createdAppointment.getId();
            appointmentDto.setId(id);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}").buildAndExpand(id)
                    .toUri();

            return ResponseEntity.created(location).body(appointmentDto);
        } else throw new BadRequestException("Patient id or Dentist id is null or invalid");
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable Long id) throws ResourceNotFoundException {
        Appointment searchedAppointment = appointmentService.getAppointmentById(id);
        if (searchedAppointment != null) {
            AppointmentDto appointmentDto = mapper.appointmentEntityToDto(searchedAppointment);

            return ResponseEntity.ok(appointmentDto);
        } else {
            throw new ResourceNotFoundException("Appointment with id %s not found".formatted(id));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointmentById(@PathVariable Long id, @RequestBody AppointmentDto appointmentUpdateDto) throws ResourceNotFoundException, BadRequestException {
        Appointment appointmentToUpdate = appointmentService.getAppointmentById(id);
        Patient patient = patientService.getPatientById(appointmentUpdateDto.getPatientId());
        Dentist dentist = dentistService.getDentistById(appointmentUpdateDto.getDentistId());

        if (appointmentToUpdate != null){
            if (dentist != null && patient != null){
                Appointment appointmentNewData = mapper.appointmentDtoToEntity(appointmentUpdateDto);
                Appointment updatedAppointment = appointmentService.updateAppointmentById(id, appointmentNewData);
                AppointmentDto updatedAppointmentDto = mapper.appointmentEntityToDto(updatedAppointment);

                return ResponseEntity.ok(updatedAppointmentDto);
            } else {
                throw new BadRequestException("Patient id or Dentist id is null or invalid");
            }

        } else {
            throw new ResourceNotFoundException("Appointment with id %s not found".formatted(id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointmentById(@PathVariable Long id) throws ResourceNotFoundException {
        Appointment appointmentToDelete = appointmentService.getAppointmentById(id);
        if (appointmentToDelete != null){
            appointmentService.deleteAppointmentById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Appointment with id %s not found".formatted(id));
        }
    }

}
