package ru.urfu.OnlineAppointment.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.urfu.OnlineAppointment.models.Patient;
import ru.urfu.OnlineAppointment.repositories.PatientRepositories;

import java.util.ArrayList;
import java.util.List;

public class PatientService {
    private final PatientRepositories patientRepositories;

    @Autowired
    public PatientService(PatientRepositories patientRepositories) {
        this.patientRepositories = patientRepositories;
    }

    public List<Patient> getPatient()
    {
        List<Patient> result = new ArrayList<>();
        patientRepositories.findAll().forEach(result::add);
        return result;
    }

    public Patient getPatientById(Long id)
    {
        return patientRepositories.findById(id).get();
    }

    public void savePatient(String name,
                            String sex,
                            int healthInsurance,
                            int age)
    {
        Patient patient = new Patient(name, sex, healthInsurance, age);
        patientRepositories.save(patient);
    }
}
