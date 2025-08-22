package com.alves.vitor.DigitalAccounts.application.usecases.account;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;

public class DeleteAccount {
    private final AccountRepository repository;

    public DeleteAccount(AccountRepository repository) {
        this.repository = repository;
    }

    public Account delete(Account account) {
        return repository.delete(account);
    }
}
