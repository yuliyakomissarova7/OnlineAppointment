package ru.urfu.OnlineAppointment.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public class AbstractUser implements IUser {
    @Id
    @GeneratedValue
    protected Long id;
    protected String name;
    protected String sex;
    protected int age;
    protected String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

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

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Set<Role> getRoles() {
        return roles;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
