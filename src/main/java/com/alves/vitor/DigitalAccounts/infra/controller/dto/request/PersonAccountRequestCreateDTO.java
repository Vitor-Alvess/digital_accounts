package com.alves.vitor.DigitalAccounts.infra.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonAccountRequestCreateDTO {
    @JsonProperty("nome")
    private String name;

    @JsonProperty("cpf")
    private String cpf;

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
}
