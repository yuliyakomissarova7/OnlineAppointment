package ru.urfu.OnlineAppointment.models;

import javax.persistence.Entity;

@Entity
public class Patient extends AbstractUser {
    private final int healthInsurance;

    public Patient(String name,
                   String sex,
                   int healthInsurance,
                   int age) {
        this.name = name;
        this.age = age;
        this.healthInsurance = healthInsurance;
        this.sex = sex;
    }

    public int getHealthInsurance() {
        return healthInsurance;
    }
}
