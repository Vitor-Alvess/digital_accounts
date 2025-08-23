package com.alves.vitor.DigitalAccounts.domain.entity;

import com.alves.vitor.DigitalAccounts.domain.enums.OperationType;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
public class Operation extends Entity{
    private Account account;
    private char type;
    private BigDecimal amount;
    private LocalDateTime time;

    public Operation(Account account, OperationType type, BigDecimal amount, LocalDateTime time) {
        this.account = account;
        this.type = type.get();
        this.amount = amount;
        this.time = time;
    }

    public Operation(Account account, OperationType type, BigDecimal amount) {
        this.account = account;
        this.type = type.get();
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
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
