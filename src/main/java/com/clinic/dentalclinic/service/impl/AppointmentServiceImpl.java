package com.clinic.dentalclinic.service.impl;

import com.clinic.dentalclinic.model.Appointment;
import com.clinic.dentalclinic.repository.AppointmentRepository;
import com.clinic.dentalclinic.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private static final Logger logger = LogManager.getLogger(AppointmentServiceImpl.class);

    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> op = appointmentRepository.findById(id);
        Appointment appointment = null;
        if (op.isPresent()){
            appointment = op.get();
        } else {
            logger.info("Appointment with id %s not found".formatted(id));
        }
        return appointment;
    }

    @Override
    public Appointment updateAppointmentById(Long id, Appointment appointmentUpdate) {
        Appointment appointment = getAppointmentById(id);
        if(appointment != null){
            appointment.setPatient(appointment.getPatient());
            appointment.setDentist(appointment.getDentist());
            appointment.setDate(appointmentUpdate.getDate());
            appointmentRepository.save(appointment);
        }
        return appointment;
    }

    @Override
    public void deleteAppointmentById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
