package com.alves.vitor.DigitalAccounts.infra.controller.dto;

import com.alves.vitor.DigitalAccounts.domain.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationDTO {
    @JsonProperty("conta")
    private AccountDTO accountDTO;

    @JsonProperty("tipo")
    private OperationType operationType;

    @JsonProperty("quantia")
    private BigDecimal amount;

    @JsonProperty("data_hora")
    private LocalDateTime time;

    public OperationDTO(AccountDTO accountDTO, char operationType, BigDecimal amount, LocalDateTime time) {
        this.accountDTO = accountDTO;
        this.operationType = operationType == 'D' ? OperationType.DEPOSITO : OperationType.SAQUE;
        this.amount = amount;
        this.time = time;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
