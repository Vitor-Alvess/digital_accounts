package com.alves.vitor.DigitalAccounts.domain.entity;

import com.alves.vitor.DigitalAccounts.domain.enums.PersonGender;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SuperBuilder
public class Person extends Entity {
    private String cpf;
    private String name;
    private char gender;
    private LocalDate birthDate;
    private String ocupation;
    private LocalDateTime modifiedAt;

    public Person(String cpf, String name, PersonGender gender, LocalDate birthDate, String ocupation) {
        this.cpf = cpf != null ? cpf.replaceAll("[.-]","") : null;
        this.name = name;
        this.gender = gender.get();
        this.birthDate = birthDate;
        this.ocupation = ocupation;
    }

    public Person(String cpf, String name, PersonGender gender, String ocupation) {
        this.cpf = cpf;
        this.name = name;
        this.gender = gender.get();
        this.ocupation = ocupation;
    }

    public Person(String cpf, String name) {
        this.cpf = cpf;
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
