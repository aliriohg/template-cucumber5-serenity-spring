package com.alirio.employe.entities;

public class GetEmployeesRegistered {

    private String company;
    private String gender;
    private String rol;
    private String from;
    private String to;

    public GetEmployeesRegistered(String company, String gender, String rol, String from, String to) {
        this.company = company;
        this.gender = gender;
        this.rol = rol;
        this.from = from;
        this.to = to;
    }

    public GetEmployeesRegistered() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
