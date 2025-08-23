package com.alves.vitor.DigitalAccounts.application.usecases.account;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;

import java.util.Random;

public class CreateAccount {
    private final AccountRepository accountRepository;
    private final PersonRepository personRepository;

    public CreateAccount(AccountRepository accountRepository, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    public Account create(Account account) {
        Person person = personRepository.findByCpf(account.getHolder().getCpf());

        if (person == null) {
            throw new RuntimeException();
        }

        if (!person.getName().equals(account.getHolder().getName())) {
            throw new RuntimeException("Os dados estão incorretos!");
        }

        account.getHolder().setID(person.getID());

        Random random = new Random();

        String agency = String.valueOf(random.nextInt(10000, 99999));
        String number = String.valueOf(random.nextLong(100000, 999999));

        account.setAgency(agency);
        account.setNumber(number);

        return accountRepository.register(account);
    }
}
