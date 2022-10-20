package ru.urfu.OnlineAppointment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.urfu.OnlineAppointment.models.Doctor;
import ru.urfu.OnlineAppointment.models.Patient;
import ru.urfu.OnlineAppointment.services.DoctorService;
import ru.urfu.OnlineAppointment.services.PatientService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EntityController {
    private final List<DoctorService> doctorServices;
    private final List<PatientService> patientServices;

    @Autowired
    public EntityController(List<DoctorService> doctorServices, List<PatientService> patientServices) {
        this.doctorServices = doctorServices;
        this.patientServices = patientServices;
    }

    @GetMapping(value = "/doctors")
    @ResponseBody
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        doctorServices.forEach(doctorServices -> doctors.addAll(doctorServices.getDoctors()));
        return doctors;

    }

    @GetMapping(value = "/patient")
    @ResponseBody
    public List<Patient> getAllPatient() {
        List<Patient> patients = new ArrayList<>();
        patientServices.forEach(patientServices -> patients.addAll(patientServices.getPatient()));
        return patients;
    }
}