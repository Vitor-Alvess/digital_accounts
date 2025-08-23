package com.alves.vitor.DigitalAccounts.domain.entity;

import com.alves.vitor.DigitalAccounts.domain.enums.AccountCurrency;
import com.alves.vitor.DigitalAccounts.domain.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SuperBuilder
public class Account extends Entity{
    private String agency;
    private String number;
    private char type;
    private BigDecimal totalAmount;
    private char currency;
    private Person holder;
    private LocalDateTime modifiedAt;

    public Account(String agency, String number, AccountType type, BigDecimal totalAmount,
                   AccountCurrency currency, String holderName, String holderCpf) {

        this.agency = agency.replace("/", "");
        this.number = number;
        this.type = type.get();
        this.totalAmount = totalAmount;
        this.currency = currency.get();
        holder = new Person(holderCpf, holderName);
    }

    public Account(Person holder, AccountType type, AccountCurrency currency) {
        this.holder = holder;
        this.type = type.get();
        this.currency = currency.get();
    }

    public Account(String agency, String number, AccountType type, AccountCurrency currency) {
        this.agency = agency;
        this.number = number;
        this.type = type.get();
        this.currency = currency.get();
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public char getCurrency() {
        return currency;
    }

    public void setCurrency(char currency) {
        this.currency = currency;
    }

    public Person getHolder() {
        return holder;
    }

    public void setHolder(Person holder) {
        this.holder = holder;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
