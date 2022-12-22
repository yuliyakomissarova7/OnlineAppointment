package ru.naumen.OnlineAppointment.enums;

public enum TimeList {
    A(9), B(10), C(11), D(12), E(13), F(14), G(15), H(16), I(17), J(18), K(19), L(20), M(21);

    private int time;

    TimeList(int time){
        this.time = time;
    }

    public int getTime() {
        return time;
    }
}
