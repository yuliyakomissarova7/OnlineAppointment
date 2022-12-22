package ru.naumen.OnlineAppointment.services.doctor;

import ru.naumen.OnlineAppointment.models.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(Long id);

    void createDoctor(Doctor doctor);

}
