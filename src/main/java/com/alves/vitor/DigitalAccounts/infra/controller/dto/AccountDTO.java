package com.alves.vitor.DigitalAccounts.infra.controller.dto;

import com.alves.vitor.DigitalAccounts.domain.enums.AccountCurrency;
import com.alves.vitor.DigitalAccounts.domain.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    @JsonProperty("titular")
    private PersonDTO holder;

    @JsonProperty("agencia")
    private String agency;

    @JsonProperty("numero_conta")
    private String number;

    @JsonProperty("tipo")
    private AccountType type;

    @JsonProperty("moeda")
    private AccountCurrency currency;

    @JsonProperty("quantia_total")
    private BigDecimal totalAmount;

    private static final String REGEX_AGENCY = "(\\d{4})(\\d)";
    private static final String PATTERN_AGENCY = "$1-$2";

    private static final String REGEX_NUMBER = "(\\d{2})(\\d{3})(\\d)";
    private static final String PATTERN_NUMBER = "$1.$2-$3";

    public AccountDTO(PersonDTO holder, String agency, String number, char type, char currency, BigDecimal totalAmount) {
        this.holder = holder;
        setAgency(agency);
        setNumber(number);
        this.type = type == 'P' ? AccountType.POUPANCA : AccountType.CORRENTE;
        this.currency = currency == 'D' ? AccountCurrency.DOLAR : AccountCurrency.REAL;
        this.totalAmount = totalAmount;
    }

    public AccountDTO(String agency, String number, BigDecimal totalAmount, char currency) {
        this.agency = agency;
        this.number = number;
        this.totalAmount = totalAmount;
        this.currency = currency == 'D' ? AccountCurrency.DOLAR : AccountCurrency.REAL;
    }

    public PersonDTO getHolder() {
        return holder;
    }

    public void setHolder(PersonDTO holder) {
        this.holder = holder;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        if (agency != null) {
            String cleanStr = agency.replaceAll("[.]", "");

            if (!cleanStr.matches("\\d{5}")) {
                throw new IllegalArgumentException("Agência inválida! A Agência deve conter apenas 5 dígitos");
            }

            this.agency = cleanStr.replaceAll(REGEX_AGENCY, PATTERN_AGENCY);
        }
        else this.agency = null;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number != null) {
            String cleanStr = number.replaceAll("[.\\-]", "");

            if (!cleanStr.matches("\\d{6}")) {
                throw new IllegalArgumentException("Número de conta inválido! O Número deve conter apenas 6 dígitos");
            }

            this.number = cleanStr.replaceAll(REGEX_NUMBER, PATTERN_NUMBER);
        }
        else this.number = null;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(AccountCurrency currency) {
        this.currency = currency;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
