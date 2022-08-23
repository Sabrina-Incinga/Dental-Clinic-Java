package com.clinic.dentalclinic.service.impl;

import com.clinic.dentalclinic.model.Dentist;
import com.clinic.dentalclinic.repository.DentistRepository;
import com.clinic.dentalclinic.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;

    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Override
    public Dentist createDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    @Override
    public Dentist getDentistById(Long id) {
        Optional<Dentist> op = dentistRepository.findById(id);
        Dentist dentist = null;
        if(op.isPresent()){
            dentist = op.get();
        } else {
            logger.info("Dentist with id %s not found".formatted(id));
        }
        return dentist;
    }

    @Override
    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist updateDentistById(Long id, Dentist dentistUpdate) {
        Dentist dentist = getDentistById(id);
        if (dentist != null){
            dentist.setFirstName(dentistUpdate.getFirstName());
            dentist.setLastName(dentistUpdate.getLastName());
            dentist.setMedicalLicenceNumber(dentistUpdate.getMedicalLicenceNumber());
            dentistRepository.save(dentist);
        } else {
            logger.info("Dentist with id %s not found".formatted(id));
        }
        return dentist;
    }

    @Override
    public void deleteDentistById(Long id) {
        dentistRepository.deleteById(id);
    }
}
