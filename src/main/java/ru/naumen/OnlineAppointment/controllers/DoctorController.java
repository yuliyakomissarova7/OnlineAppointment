package ru.naumen.OnlineAppointment.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.naumen.OnlineAppointment.enums.TimeList;
import ru.naumen.OnlineAppointment.models.Appointment;
import ru.naumen.OnlineAppointment.models.Doctor;
import ru.naumen.OnlineAppointment.services.doctor.DoctorService;
import ru.naumen.OnlineAppointment.services.appointment.AppointmentService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/doctors")
@AllArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final AppointmentService timeService;

    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctorService.getAllDoctors());
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String allDoctors(Model model){
        List<Doctor> doctors = doctorService.getAllDoctors();

        model.addAttribute("doctors", doctors);

        return "mainPage";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String createDoctor_(@ModelAttribute Doctor doctor, Model model){
        model.addAttribute("doctor", doctor);
        doctorService.createDoctor(doctor);
        return "redirect:/addDoctor";
    }

    @GetMapping("/{doctor_id}")
    public String getDoctorPage(@PathVariable Long doctor_id, Model model) {
        Doctor doctor = doctorService.getDoctorById(doctor_id);

        model.addAttribute("doctor", doctor);
        model.addAttribute("times", TimeList.values());
        return "doctorPage";
    }

    @PostMapping("/{doctor_id}")
    public String getTime(@PathVariable Long doctor_id, Model model, @ModelAttribute List<Appointment> date){
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
//        Date date_ = formatter.parse(date.date);
        Doctor doctor = doctorService.getDoctorById(doctor_id);
        doctor.setTime(date);
        model.addAttribute("times", date);
        return "doctorPage";
    }

}
