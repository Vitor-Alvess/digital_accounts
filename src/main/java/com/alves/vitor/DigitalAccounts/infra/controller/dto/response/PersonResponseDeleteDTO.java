package com.alves.vitor.DigitalAccounts.infra.controller.dto.response;

import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonResponseDeleteDTO extends PersonDTO {
    @JsonProperty("deletado_em")
    private LocalDateTime deletedAt;

    public PersonResponseDeleteDTO(String name, String cpf, LocalDateTime deletedAt) {
        super(name, cpf);
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
