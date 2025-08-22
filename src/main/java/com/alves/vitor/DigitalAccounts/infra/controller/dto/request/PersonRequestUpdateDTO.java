package com.alves.vitor.DigitalAccounts.infra.controller.dto.request;

import com.alves.vitor.DigitalAccounts.domain.enums.PersonGender;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonRequestUpdateDTO {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("sexo")
    private PersonGender gender;

    @JsonProperty("profissao")
    private String ocupation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonGender getGender() {
        return gender;
    }

    public void setGender(PersonGender gender) {
        this.gender = gender;
    }

    public String getOcupation() {
        return ocupation;
    }

    public void setOcupation(String ocupation) {
        this.ocupation = ocupation;
    }
}
