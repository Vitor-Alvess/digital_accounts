package com.alves.vitor.DigitalAccounts.infra.controller.dto.response.account;

import com.alves.vitor.DigitalAccounts.infra.controller.dto.AccountDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseDeleteDTO extends AccountDTO {

    @JsonProperty("deletado_em")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime deletedAt;

    public AccountResponseDeleteDTO(PersonDTO holder, String agency, String number, char type, char currency, BigDecimal totalAmount, LocalDateTime deletedAt) {
        super(holder, agency, number, type, currency, totalAmount);
        this.deletedAt = deletedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
