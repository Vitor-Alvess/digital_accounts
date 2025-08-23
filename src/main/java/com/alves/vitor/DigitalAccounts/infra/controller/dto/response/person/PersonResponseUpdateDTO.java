package com.alves.vitor.DigitalAccounts.infra.controller.dto.response.person;

import com.alves.vitor.DigitalAccounts.domain.enums.PersonGender;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseUpdateDTO {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("sexo")
    private PersonGender gender;

    @JsonProperty("profissao")
    private String ocupation;

    public PersonResponseUpdateDTO(String name, String cpf, char gender, String ocupation) {
        this.name = name;
        this.cpf = cpf;
        this.gender = gender == 'M' ? PersonGender.MASCULINO : PersonGender.FEMININO;
        this.ocupation = ocupation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
