package ru.naumen.OnlineAppointment.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctor")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String specialization;

    @Column
    private Long experience;

    @Column(length = 1000)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
    private List<Appointment> time;

    @Column
    private byte[] image;

}
