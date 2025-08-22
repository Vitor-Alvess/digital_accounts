package com.alves.vitor.DigitalAccounts.domain.entity;

import java.math.BigDecimal;

public class Operation {
    private String accountAgency;
    private String accountNumber;
    private char type;
    private BigDecimal value;

    public Operation(String accountAgency, String accountNumber, char type, BigDecimal value) {
        this.accountAgency = accountAgency.replace("/", "");
        this.accountNumber = accountNumber;
        this.type = type;
        this.value = value;
    }

    public String getAccountAgency() {
        return accountAgency;
    }

    public void setAccountAgency(String accountAgency) {
        this.accountAgency = accountAgency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
