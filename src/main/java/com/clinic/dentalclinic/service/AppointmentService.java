package com.clinic.dentalclinic.service;

import com.clinic.dentalclinic.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);

    Appointment getAppointmentById(Long id);


    Appointment updateAppointmentById(Long id, Appointment appointmentUpdate);

    void deleteAppointmentById(Long id);
}
