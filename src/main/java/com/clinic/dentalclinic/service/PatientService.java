package com.clinic.dentalclinic.service;

import com.clinic.dentalclinic.model.Patient;

import java.util.List;

public interface PatientService {
    Patient createPatient(Patient patient);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();
    Patient updatePatientById(Long id, Patient patientUpdate);

    void deletePatientById(Long id);
}
