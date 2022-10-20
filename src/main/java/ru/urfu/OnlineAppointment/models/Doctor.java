package ru.urfu.OnlineAppointment.models;

import javax.persistence.Entity;

@Entity
public class Doctor extends AbstractUser {
    private final String specialization;
    private final int seniority;

    public Doctor(
            String name,
            String specialization,
            String sex,
            int seniority,
            int age) {
        this.name = name;
        this.specialization = specialization;
        this.seniority = seniority;
        this.age = age;
        this.sex = sex;
    }


    public String getSpecialization() {
        return specialization;
    }

    public int getSeniority() {
        return seniority;
    }
}
