package com.clinic.dentalclinic.controller;

import com.clinic.dentalclinic.dto.DentistDto;
import com.clinic.dentalclinic.exceptions.ResourceNotFoundException;
import com.clinic.dentalclinic.mapper.DentistMapper;
import com.clinic.dentalclinic.model.Dentist;
import com.clinic.dentalclinic.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dentists")
@RequiredArgsConstructor
public class DentistController {
    private final DentistService dentistService;

    private final DentistMapper dentistMapper;

    @PostMapping
    public ResponseEntity<Dentist> createNewDentist(@RequestBody DentistDto dentistDto){
        Dentist dentist = dentistMapper.dentistDtoToEntity(dentistDto);
        Dentist createdDentist = dentistService.createDentist(dentist);
        final long id = createdDentist.getId();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(createdDentist);
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> getAllDentists(){
        return ResponseEntity.ok(dentistService.getAllDentists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getDentistById(@PathVariable Long id) throws ResourceNotFoundException {
        Dentist searchedDentist = dentistService.getDentistById(id);
        if (searchedDentist != null){
            return ResponseEntity.ok(searchedDentist);
        } else {
            throw new ResourceNotFoundException("Dentist with id %s not found".formatted(id));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dentist> updateDentist(@PathVariable Long id, @RequestBody DentistDto dentistDto) throws ResourceNotFoundException {
        Dentist dentist = dentistService.getDentistById(id);
        Dentist dentistUpdate = dentistMapper.dentistDtoToEntity(dentistDto);
        if (dentist != null){
            return ResponseEntity.ok(dentistService.updateDentistById(id,dentistUpdate));
        } else {
            throw new ResourceNotFoundException("Dentist with id %s not found".formatted(id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDentistById(@PathVariable Long id) throws ResourceNotFoundException {
        Dentist dentistToDelete = dentistService.getDentistById(id);
        if (dentistToDelete != null){
            dentistService.deleteDentistById(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("Dentist with id %s not found".formatted(id));
        }
    }

}
