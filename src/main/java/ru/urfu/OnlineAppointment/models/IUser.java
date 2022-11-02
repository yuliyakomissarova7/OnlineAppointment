package ru.urfu.OnlineAppointment.models;

import java.util.Set;

public interface IUser {
    String getName();

    Long getId();

    int getAge();

    String getSex();

    String getPassword();

    Set<Role> getRoles();

    void setId(Long id);

    void setPassword(String password);

    void setRoles(Set<Role> roles);

}
