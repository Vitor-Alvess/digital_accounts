package com.alves.vitor.DigitalAccounts.infra.controller.dto.request;

import com.alves.vitor.DigitalAccounts.domain.enums.AccountCurrency;
import com.alves.vitor.DigitalAccounts.domain.enums.AccountType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountRequestUpdateDTO {

    @JsonProperty("tipo")
    private AccountType accountType;

    @JsonProperty("câmbio")
    private AccountCurrency accountCurrency;

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public AccountCurrency getAccountCurrency() {
        return accountCurrency;
    }

    public void setAccountCurrency(AccountCurrency accountCurrency) {
        this.accountCurrency = accountCurrency;
    }
}
