package ru.urfu.OnlineAppointment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.urfu.OnlineAppointment.models.Doctor;
import ru.urfu.OnlineAppointment.repositories.DoctorRepositories;

import java.util.ArrayList;
import java.util.List;


@Component
public class DoctorService {
    private final DoctorRepositories doctorRepositories;

    @Autowired
    public DoctorService(DoctorRepositories doctorRepositories) {
        this.doctorRepositories = doctorRepositories;
    }

    public List<Doctor> getDoctors() {
        List<Doctor> result = new ArrayList<>();
        doctorRepositories.findAll().forEach(result::add);
        return result;
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepositories.findById(id).get();
    }

    public void saveDoctor(String name,
                           String specialization,
                           String sex,
                           int seniority,
                           int age) {
        Doctor doctor = new Doctor(name, specialization, sex, seniority, age);
        doctorRepositories.save(doctor);
    }
}
