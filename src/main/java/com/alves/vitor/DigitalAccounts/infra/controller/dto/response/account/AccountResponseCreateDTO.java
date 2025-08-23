package com.alves.vitor.DigitalAccounts.infra.controller.dto.response.account;

import com.alves.vitor.DigitalAccounts.infra.controller.dto.AccountDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountResponseCreateDTO extends AccountDTO {

    @JsonProperty("data_criacao")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime createdAt;

    public AccountResponseCreateDTO(PersonDTO holder, String agency, String number, char type, char currency, BigDecimal totalAmount, LocalDateTime createdAt) {
        super(holder, agency, number, type, currency, totalAmount);
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
