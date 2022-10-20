package ru.urfu.OnlineAppointment.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.urfu.OnlineAppointment.models.Patient;

public interface PatientRepositories extends CrudRepository<Patient, Long> {
}