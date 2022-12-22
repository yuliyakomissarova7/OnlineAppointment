package ru.naumen.OnlineAppointment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.naumen.OnlineAppointment.models.Appointment;
import ru.naumen.OnlineAppointment.models.User;
import ru.naumen.OnlineAppointment.services.appointment.AppointmentService;
import ru.naumen.OnlineAppointment.services.user.UserService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@RequiredArgsConstructor
@RequestMapping(path = "/user")
@Controller
public class UserController {

    private final UserService userService;

    private final AppointmentService appointmentService;

    @GetMapping("/lk")
    public String lk(@CookieValue(value = "user_id") Long userId, Long appointmentId, Model model) {
        model.addAttribute("appointments", appointmentService.getAppointments(userId));
        model.addAttribute("user", userService.getUserById(userId));
        return "userPage";
    }

    @PostMapping("/lk")
    public String createAppointment(@CookieValue(value = "user_id") Long userId, Appointment appointment, Model model) {
        appointmentService.saveAppointment(appointment, userId);
        return "userPage";
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }


    private HashMap<String, Object> getAppointmentAttributes(Long userId, Long appointmentId) {
        var attributes = new HashMap<String, Object>();

        var appointments = appointmentService.getAppointments(userId);
        attributes.put("appointments", appointments);

        var appointment = appointments.stream()
                .filter(ap -> ap.getId().equals(appointmentId))
                .findFirst()
                .orElse(null);

        attributes.put("appointmentId", appointmentId);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        attributes.put("appointmentDate", appointment != null ? formatter.format(appointment.getDate()) : null);

        return attributes;
    }

}
