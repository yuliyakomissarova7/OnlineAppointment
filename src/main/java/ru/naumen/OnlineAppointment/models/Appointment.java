package ru.naumen.OnlineAppointment.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.naumen.OnlineAppointment.enums.TimeList;
import ru.naumen.OnlineAppointment.enums.TimeStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appointment")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private TimeList time;

    @ElementCollection(targetClass = TimeStatus.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<TimeStatus> status = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
