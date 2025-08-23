package com.alves.vitor.DigitalAccounts.infra.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OperationAccountRequestCreateDTO {
    @JsonProperty("agencia")
    private String agency;

    @JsonProperty("numero")
    private String number;

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
