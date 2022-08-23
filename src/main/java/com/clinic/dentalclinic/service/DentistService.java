package com.clinic.dentalclinic.service;

import com.clinic.dentalclinic.model.Dentist;

import java.util.List;

public interface DentistService {
    Dentist createDentist(Dentist dentist);

    Dentist getDentistById(Long id);

    List<Dentist> getAllDentists();

    Dentist updateDentistById(Long id, Dentist dentistUpdate);

    void deleteDentistById(Long id);
}
