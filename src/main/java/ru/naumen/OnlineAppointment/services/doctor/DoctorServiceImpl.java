package ru.naumen.OnlineAppointment.services.doctor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.naumen.OnlineAppointment.exeptions.UserNotFoundException;
import ru.naumen.OnlineAppointment.models.Doctor;
import ru.naumen.OnlineAppointment.repositories.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> result = new ArrayList<>();
        doctorRepository.findAll().forEach(result::add);
        return result;
    }
    
    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public void createDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

}
