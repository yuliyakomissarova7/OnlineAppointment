package ru.naumen.OnlineAppointment.services.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.naumen.OnlineAppointment.models.Appointment;
import ru.naumen.OnlineAppointment.models.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    void addAppointment(Appointment appointment, Long userId);

    User getUserById(Long id);

    User getUserByEmail(String email);

    boolean isAdminExist(User admin);

    void createAdmin();

    void createUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    List<User> getUserList(Long idMin);
}
