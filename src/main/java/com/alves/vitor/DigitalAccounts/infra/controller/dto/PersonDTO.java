package com.alves.vitor.DigitalAccounts.infra.controller.dto;

import com.alves.vitor.DigitalAccounts.domain.enums.PersonGender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({
        "nome",
        "cpf",
        "sexo",
        "data_nascimento",
        "profissao"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDTO {
    @JsonProperty("nome")
    private String name;

    private String cpf;

    @JsonProperty("sexo")
    private PersonGender gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("data_nascimento")
    private LocalDate birthDate;

    @JsonProperty("profissao")
    private String ocupation;

    private static final String REGEX_CPF = "(\\d{3})(\\d{3})(\\d{3})(\\d{2})";
    private static final String FINAL_PATTERN = "$1.$2.$3-$4";

    public PersonDTO() {}

    public PersonDTO(String name, String cpf, char gender, LocalDate birthDate, String ocupation) {
        this.name = name;
        setCpf(cpf);
        this.gender = gender == 'M' ? PersonGender.MASCULINO : PersonGender.FEMININO;
        this.birthDate = birthDate;
        this.ocupation = ocupation;
    }

    public PersonDTO(String name, String cpf, char gender, String ocupation) {
        this.name = name;
        setCpf(cpf);
        this.gender = gender == 'M' ? PersonGender.MASCULINO : PersonGender.FEMININO;
        this.ocupation = ocupation;
    }

    public PersonDTO(String name, String cpf, String rg) {
        this.name = name;
        setCpf(cpf);
    }

    public PersonDTO(String name, String cpf) {
        this.name = name;
        setCpf(cpf);
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
        if (cpf != null) {
            String cleanCpf = cpf.replaceAll("[.\\-]", "");

            if (!cleanCpf.matches("\\d{11}")) {
                throw new IllegalArgumentException("CPF inválido! O CPF deve conter apenas 11 dígitos");
            }

            this.cpf = cleanCpf.replaceAll(REGEX_CPF, FINAL_PATTERN);
        }
        else this.cpf = null;
    }

    public PersonGender getGender() {
        return gender;
    }

    public void setGender(PersonGender gender) {
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
