package ru.naumen.OnlineAppointment.services.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.naumen.OnlineAppointment.enums.Role;
import ru.naumen.OnlineAppointment.exeptions.UserExistException;
import ru.naumen.OnlineAppointment.exeptions.UserNotFoundException;
import ru.naumen.OnlineAppointment.models.Appointment;
import ru.naumen.OnlineAppointment.models.User;
import ru.naumen.OnlineAppointment.repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User myUser = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(myUser.getEmail(), myUser.getPassword(), mapRolesToAthorities(myUser.getRoles()));
    }

    @Override
    @Transactional
    public void addAppointment(Appointment appointment, Long userId) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.getAppointment().add(appointment);
        try {
            userRepository.save(user);
        } catch (Exception ignored) {

        }
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean isAdminExist(User admin){
        return admin.getEmail() != null;
    }

    @Override
    public void createAdmin() {
        User admin = new User();
        admin.setRoles(Collections.singleton(Role.Admin));
        admin.setEmail("admin@email.ru");
        admin.setPassword("admin");
        admin.setFirstName("admin");
        admin.setSecondName("admin");
        admin.setPatronymic("admin");
        userRepository.save(admin);
    }

    @Override
    public void createUser(User user) {
        if (userRepository.findByEmail(user.getUsername()) != null) throw new UserExistException();
        user.setRoles(Collections.singleton(Role.User));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ignored) {  }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }

}
