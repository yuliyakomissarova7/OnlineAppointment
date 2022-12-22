package ru.naumen.OnlineAppointment.services.appointment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.OnlineAppointment.exeptions.AppointmentNotFoundException;
import ru.naumen.OnlineAppointment.models.Appointment;
import ru.naumen.OnlineAppointment.repositories.AppointmentRepository;
import ru.naumen.OnlineAppointment.services.user.UserService;

import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private final UserService userService;

    @Override
    @Transactional
    public Appointment saveAppointment(Appointment appointment, Long userId) {
        appointment.setUser(userService.getUserById(userId));
        var appointment_ = appointmentRepository.save(appointment);
        userService.addAppointment(appointment_, userId);
        return appointment_;
    }

    @Override
    public Appointment getAppointmentByDoctorId(Long doctor_id) {
        return appointmentRepository.findByDoctorId(doctor_id);
    }

    @Override
    public Appointment getAppointmentByUserId(Long user_id) {
        return appointmentRepository.findByUserId(user_id);
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).orElseThrow(AppointmentNotFoundException::new);
    }

    @Override
    public List<Appointment> getAppointments(Long userId) {
        return userService.getUserById(userId).getAppointment();
    }
}
