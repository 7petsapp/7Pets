package com.noon.a7pets.models;

public class ClinicReservation {

    private String reason;
    private String date;
    private String time;

    public ClinicReservation() {
    }

    public ClinicReservation(String reason, String date, String time) {
        this.reason = reason;
        this.date = date;
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
