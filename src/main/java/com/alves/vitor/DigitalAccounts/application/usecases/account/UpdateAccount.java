package com.alves.vitor.DigitalAccounts.application.usecases.account;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;

public class UpdateAccount {
    private AccountRepository repository;

    public UpdateAccount(AccountRepository repository) {
        this.repository = repository;
    }

    public Account update(Account account) {
        return repository.update(account);
    }
}
