package com.alves.vitor.DigitalAccounts.application.usecases.account;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;

public class CreateAccount {
    private final AccountRepository repository;

    public CreateAccount(AccountRepository repository) {
        this.repository = repository;
    }

    public Account create(Account account) {
        return repository.register(account);
    }
}
