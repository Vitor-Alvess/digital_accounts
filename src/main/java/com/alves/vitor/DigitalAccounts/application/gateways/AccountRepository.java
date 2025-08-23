package com.alves.vitor.DigitalAccounts.application.gateways;

import com.alves.vitor.DigitalAccounts.domain.entity.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> findByAgency(String agency);

    List<Account> findByCpf(String cpf);

    Account findByAgencyAndNumber(String agency, String number);

    Account register(Account account);

    Account update(Account newAccount);

    Account delete(String agency, String number);
}
