package com.alves.vitor.DigitalAccounts.application.usecases.account;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.enums.AccountCurrency;
import com.alves.vitor.DigitalAccounts.domain.enums.AccountType;
import com.alves.vitor.DigitalAccounts.domain.exceptions.AccountNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UpdateAccount {
    private AccountRepository repository;

    public UpdateAccount(AccountRepository repository) {
        this.repository = repository;
    }

    public Account update(Account newAccount) {
        Account persistedAccount = repository.findByAgencyAndNumber(newAccount.getAgency(), newAccount.getNumber());

        if (persistedAccount == null) {
            throw new AccountNotFoundException();
        }

        char persistedAccountCurrency = persistedAccount.getCurrency();
        char newAccountCurrency = newAccount.getCurrency();

        BigDecimal oldAmmount = persistedAccount.getTotalAmount();

        if (persistedAccountCurrency != newAccountCurrency) {
            if (persistedAccountCurrency == AccountCurrency.DOLAR.get()) {
                newAccount.setTotalAmount(oldAmmount.divide(BigDecimal.valueOf(5.40), RoundingMode.HALF_EVEN));
            }
            else {
                newAccount.setTotalAmount(oldAmmount.multiply(BigDecimal.valueOf(5.40)));
            }
        }
        else {
            newAccount.setTotalAmount(oldAmmount);
        }

        return repository.update(newAccount);
    }
}
