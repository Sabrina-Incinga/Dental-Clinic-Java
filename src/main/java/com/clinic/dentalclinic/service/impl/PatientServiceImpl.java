package com.clinic.dentalclinic.service.impl;

import com.clinic.dentalclinic.model.Patient;
import com.clinic.dentalclinic.repository.PatientRepository;
import com.clinic.dentalclinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientById(Long id) {
        Optional<Patient> op = patientRepository.findById(id);
        Patient patient = null;
        if (op.isPresent()){
            patient = op.get();

        } else {
            logger.info("Patient with id %s not found".formatted(id));
        }
        return patient;
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient updatePatientById(Long id, Patient patientUpdate) {
        Patient patient = getPatientById(id);
        if (patient != null){
            patient.setFirstName(patientUpdate.getFirstName());
            patient.setLastName(patientUpdate.getLastName());
            patient.setIdentityCardNumber(patientUpdate.getIdentityCardNumber());
            patient.setAddress(patientUpdate.getAddress());
            patient.setEmail(patientUpdate.getEmail());
            patient.setAdmissionDate(patientUpdate.getAdmissionDate());
            patientRepository.save(patient);
        } else {
            logger.info("Patient with id %s not found".formatted(id));
        }
        return patient;
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}
