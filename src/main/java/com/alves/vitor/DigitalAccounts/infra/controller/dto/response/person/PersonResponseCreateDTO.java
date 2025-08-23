package com.alves.vitor.DigitalAccounts.infra.controller.dto.response.person;

import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseCreateDTO extends PersonDTO {
    @JsonProperty("data_criacao")
    private LocalDateTime creationDateTime;

    public PersonResponseCreateDTO(String name, String cpf, LocalDateTime creationDateTime) {
        super(name, cpf);
        this.creationDateTime = creationDateTime;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
