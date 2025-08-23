package com.alves.vitor.DigitalAccounts.infra.controller.dto.response.person;

import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseUpdateDTO extends PersonDTO {

    public PersonResponseUpdateDTO(String name, String cpf, char gender, String ocupation) {
        super(name, cpf, gender, ocupation);
    }
}
