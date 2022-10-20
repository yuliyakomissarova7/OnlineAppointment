package ru.urfu.OnlineAppointment.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.urfu.OnlineAppointment.models.Doctor;

public interface DoctorRepositories extends CrudRepository<Doctor, Long> {
}
