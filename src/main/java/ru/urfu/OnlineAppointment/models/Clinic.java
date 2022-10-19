package ru.urfu.OnlineAppointment.models;

public class Clinic {
    private final String name;
    private final String address;
    private final String profile;
    private final int phoneNumber;

    public Clinic(
            String name,
            String address,
            String profile,
            int phoneNumber) {
        this.name = name;
        this.address = address;
        this.profile = profile;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getProfile() {
        return profile;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
}
