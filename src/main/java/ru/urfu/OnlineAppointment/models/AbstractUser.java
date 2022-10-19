package ru.urfu.OnlineAppointment.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractUser implements IUser {
    protected String name;
    protected String sex;
    protected int age;

    @Id
    @GeneratedValue
    protected Long id;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getSex() {
        return sex;
    }
}
