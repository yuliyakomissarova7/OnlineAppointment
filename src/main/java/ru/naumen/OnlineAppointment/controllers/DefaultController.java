package ru.naumen.OnlineAppointment.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.naumen.OnlineAppointment.enums.Specialization;
import ru.naumen.OnlineAppointment.exeptions.UserExistException;
import ru.naumen.OnlineAppointment.models.Doctor;
import ru.naumen.OnlineAppointment.models.User;
import ru.naumen.OnlineAppointment.services.doctor.DoctorService;
import ru.naumen.OnlineAppointment.services.user.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DefaultController {

    private final UserService userService;
    private final DoctorService doctorService;

    @GetMapping(value = "/")
    public String homePage(HttpServletResponse response, Model model){
        var currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        var currentUserId = userService.getUserByEmail(currentUserName).getId();
        var cookie = new Cookie("user_id", currentUserId.toString());
        response.addCookie(cookie);
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        model.addAttribute("specializations", Specialization.values());
        return "mainPage";
    }

    @PostMapping("/")
    public String filter(@RequestParam String spec, Model model, HttpServletResponse response){

        return "mainPage";
    }

    @GetMapping(value = "/login")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String create(User user, Model model) {
        try {
            userService.createUser(user);
            return "redirect:/login";
        } catch (UserExistException ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
