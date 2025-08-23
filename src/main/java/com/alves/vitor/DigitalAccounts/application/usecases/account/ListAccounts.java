package com.alves.vitor.DigitalAccounts.application.usecases.account;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;

import java.util.List;

public class ListAccounts {
    private final AccountRepository repository;

    public ListAccounts(AccountRepository repository) {
        this.repository = repository;
    }

    public List<Account> findByAgency(String agency) {
        return repository.findByAgency(agency);
    }

    public List<Account> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public Account findByAgencyAndNumber (String agency, String number) {
        return repository.findByAgencyAndNumber(agency, number);
    }
}
