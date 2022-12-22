package ru.naumen.OnlineAppointment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.naumen.OnlineAppointment.enums.Specialization;
import ru.naumen.OnlineAppointment.enums.TimeList;
import ru.naumen.OnlineAppointment.models.Doctor;
import ru.naumen.OnlineAppointment.services.doctor.DoctorService;
import ru.naumen.OnlineAppointment.services.user.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {
    private final UserService userService;
    private final DoctorService doctorService;

    @GetMapping("/")
    public String userList(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "adminPage";
    }

    @PostMapping("/")
    public String deleteUser(@RequestParam(required = true, defaultValue = "" ) Long userId,
                              @RequestParam(required = true, defaultValue = "" ) String action, Model model) {
        if (action.equals("delete")){
            userService.deleteUser(userId);
        }
        return "redirect:/admin";
    }

    @RequestMapping(value="/addDoctor", method=RequestMethod.GET)
    public String addDoctorForm(Model model) {
        model.addAttribute("specializations", Specialization.values());
        model.addAttribute("doctor", new Doctor());

        return "addDoctor";
    }

    @GetMapping("/doctors/{doctorId}/addDateTime")
    public String addDateTime(@PathVariable Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("times", TimeList.values());
        return "addDateTime";
    }

    @PostMapping("/doctors/{doctorId}/addDateTime")
    public String addDatetime(@PathVariable Long doctorId, @ModelAttribute Doctor doctor, Model model) {
        return "addDateTime";
    }


    @RequestMapping(value="/addDoctor", method=RequestMethod.POST)
    public String createDoctor(@ModelAttribute Doctor doctor, Model model){
        model.addAttribute("doctor", doctor);
        doctorService.createDoctor(doctor);
        return "redirect:/";
    }

    @GetMapping("/get/{userId}")
    public String  getUser(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("users", userService.getUserList(userId));
        return "adminPage";
    }
}
