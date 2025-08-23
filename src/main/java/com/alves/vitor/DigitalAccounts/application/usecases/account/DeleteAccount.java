package com.alves.vitor.DigitalAccounts.application.usecases.account;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.exceptions.AccountNotFoundException;

public class DeleteAccount {
    private final AccountRepository repository;

    public DeleteAccount(AccountRepository repository) {
        this.repository = repository;
    }

    public Account delete(String agency, String number) {
        Account exists = repository.findByAgencyAndNumber(agency, number);

        if (exists == null) {
            throw new AccountNotFoundException();
        }

        return repository.delete(agency, number);
    }
}
