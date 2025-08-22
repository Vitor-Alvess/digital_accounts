package com.alves.vitor.DigitalAccounts.domain.entity;

import java.time.LocalDate;

public class Person {
    private String cpf;
    private String rg;
    private String name;
    private char gender;
    private LocalDate birthDate;
    private String ocupation;

    public Person(String cpf, String rg, String name, char gender, LocalDate birthDate, String ocupation) {
        this.cpf = cpf.replace(".", "").replace("-", "");
        this.rg = rg.replace(".", "").replace("-", "");
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.ocupation = ocupation;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }
}
