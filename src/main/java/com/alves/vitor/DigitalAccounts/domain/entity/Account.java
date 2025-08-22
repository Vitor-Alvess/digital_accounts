package com.alves.vitor.DigitalAccounts.domain.entity;

import com.alves.vitor.DigitalAccounts.domain.enums.AccountType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account {
    private String agency;
    private String number;
    private char type;
    private BigDecimal balance;
    private char currency;
    private String holderRg;
    private String holderCpf;
    private LocalDateTime creationDate;

    public Account(String agency, String number, AccountType type, BigDecimal balance, char currency, String holderRg, String holderCpf) {
        this.agency = agency.replace("/", "");
        this.number = number;
        this.type = type.get().charAt(0);
        this.balance = balance;
        this.currency = currency;
        this.holderRg = holderRg.replace(".", "").replace("-", "");
        this.holderCpf = holderCpf.replace(".", "").replace("-", "");
        creationDate = LocalDateTime.now();
    }

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

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public char getCurrency() {
        return currency;
    }

    public void setCurrency(char currency) {
        this.currency = currency;
    }

    public String getHolderRg() {
        return holderRg;
    }

    public void setHolderRg(String holderRg) {
        this.holderRg = holderRg;
    }

    public String getHolderCpf() {
        return holderCpf;
    }

    public void setHolderCpf(String holderCpf) {
        this.holderCpf = holderCpf;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
