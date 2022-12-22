package ru.naumen.OnlineAppointment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.OnlineAppointment.models.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    List<Doctor> findBySpecialization(String specialization);
}
