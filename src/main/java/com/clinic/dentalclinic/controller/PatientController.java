package com.clinic.dentalclinic.controller;

import com.clinic.dentalclinic.dto.PatientDto;
import com.clinic.dentalclinic.exceptions.ResourceNotFoundException;
import com.clinic.dentalclinic.mapper.PatientMapper;
import com.clinic.dentalclinic.model.Address;
import com.clinic.dentalclinic.model.Patient;
import com.clinic.dentalclinic.service.AddressService;
import com.clinic.dentalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final PatientService patientService;

    private final AddressService addressService;

    private final PatientMapper patientMapper;

    @PostMapping
    public ResponseEntity<Patient> createNewPatient(@RequestBody PatientDto patientDto){
        Patient patientToSave = patientMapper.patientDtoToEntity(patientDto);
        addressService.createAddress(patientDto.getAddress());
        Patient createdPatient = patientService.createPatient(patientToSave);

        final long id = createdPatient.getId();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(createdPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws ResourceNotFoundException {
        Patient searchedPatient = patientService.getPatientById(id);

        if (searchedPatient != null){
            return ResponseEntity.ok(searchedPatient);
        } else {
            throw new ResourceNotFoundException("Patient with id %s not found".formatted(id));
        }

    }

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatientById(@PathVariable Long id, @RequestBody PatientDto patientDto) throws ResourceNotFoundException {
        Patient patientToUpdate = patientService.getPatientById(id);
        Patient dataToUpdate = patientMapper.patientDtoToEntity(patientDto);

        if (patientToUpdate != null){
            Address addressToUpdate = patientToUpdate.getAddress();
            dataToUpdate.getAddress().setId(addressToUpdate.getId());
            addressService.updateAddressById(addressToUpdate.getId(), dataToUpdate.getAddress());
            return ResponseEntity.ok(patientService.updatePatientById(id, dataToUpdate));
        } else {
            throw new ResourceNotFoundException("Patient with id %s not found".formatted(id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id) throws ResourceNotFoundException {
        Patient patientToDelete = patientService.getPatientById(id);
        if (patientToDelete != null){
            patientService.deletePatientById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Patient with id %s not found".formatted(id));
        }
    }
}
