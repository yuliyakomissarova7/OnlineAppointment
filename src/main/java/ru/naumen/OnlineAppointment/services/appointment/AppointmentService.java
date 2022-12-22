package ru.naumen.OnlineAppointment.services.appointment;

import ru.naumen.OnlineAppointment.models.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment saveAppointment(Appointment appointment, Long userId);

    Appointment getAppointmentByDoctorId(Long doctor_id);

    Appointment getAppointmentByUserId(Long user_id);

    Appointment getAppointment(Long id);

    List<Appointment> getAppointments(Long userId);
}
