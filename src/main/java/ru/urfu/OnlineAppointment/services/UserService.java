package ru.urfu.OnlineAppointment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.urfu.OnlineAppointment.models.Patient;
import ru.urfu.OnlineAppointment.models.Role;
import ru.urfu.OnlineAppointment.repositories.PatientRepositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserService implements UserDetailsService {
    private final PatientRepositories userRepository;

    @Autowired
    public UserService(PatientRepositories userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Patient myUser = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(myUser.getName(), myUser.getPassword(),
                mapRolesToAthorities(myUser.getRoles()));
    }

    public List<Patient> getPatient() {
        List<Patient> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }

    public Patient getPatientById(Long id) {
        return userRepository.findById(id).get();
    }

    public void savePatient(String name,
                            String sex,
                            int healthInsurance,
                            int age) {
        Patient patient = new Patient(name, sex, healthInsurance, age);
        userRepository.save(patient);
    }

    private List<? extends GrantedAuthority> mapRolesToAthorities(Set<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toList());
    }

    public void addUser(Patient user) throws Exception {
        Patient userFromDb = userRepository.findByUsername(user.getName());
        if (userFromDb != null) {
            throw new Exception("user exist");
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
    }

}
