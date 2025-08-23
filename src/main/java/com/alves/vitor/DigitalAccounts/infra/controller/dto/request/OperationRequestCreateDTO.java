package com.alves.vitor.DigitalAccounts.infra.controller.dto.request;

import com.alves.vitor.DigitalAccounts.domain.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class OperationRequestCreateDTO {
    @JsonProperty("conta")
    private OperationAccountRequestCreateDTO accountDTO;

    @JsonProperty("tipo")
    private OperationType operationType;

    @JsonProperty("quantia")
    private BigDecimal quantia;

    public OperationAccountRequestCreateDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(OperationAccountRequestCreateDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getQuantia() {
        return quantia;
    }

    public void setQuantia(BigDecimal quantia) {
        this.quantia = quantia;
    }
}
