package ru.naumen.OnlineAppointment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.OnlineAppointment.models.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    Appointment findByDoctorId(Long doctor_id);

    Appointment findByUserId(Long user_id);

}
