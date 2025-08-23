package com.alves.vitor.DigitalAccounts.infra.controller.dto.request;

import com.alves.vitor.DigitalAccounts.domain.enums.AccountCurrency;
import com.alves.vitor.DigitalAccounts.domain.enums.AccountType;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequestCreateDTO {
    @JsonProperty("titular")
    private PersonAccountRequestCreateDTO accountHolder;

    @JsonProperty("tipo")
    private AccountType accountType;

    @JsonProperty("câmbio")
    private AccountCurrency currency;

    public PersonAccountRequestCreateDTO getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(PersonAccountRequestCreateDTO accountHolder) {
        this.accountHolder = accountHolder;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(AccountCurrency currency) {
        this.currency = currency;
    }
}
