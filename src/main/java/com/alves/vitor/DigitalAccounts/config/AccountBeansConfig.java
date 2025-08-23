package com.alves.vitor.DigitalAccounts.config;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.application.usecases.account.CreateAccount;
import com.alves.vitor.DigitalAccounts.application.usecases.account.DeleteAccount;
import com.alves.vitor.DigitalAccounts.application.usecases.account.ListAccounts;
import com.alves.vitor.DigitalAccounts.application.usecases.account.UpdateAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountBeansConfig {

    @Bean
    ListAccounts listAccounts(AccountRepository repository) {
        return new ListAccounts(repository);
    }

    @Bean
    CreateAccount createAccount(AccountRepository accountRepository, PersonRepository personRepository) {
        return new CreateAccount(accountRepository, personRepository);
    }

    @Bean
    UpdateAccount updateAccount(AccountRepository repository) {
        return new UpdateAccount(repository);
    }

    @Bean
    DeleteAccount deleteAccount(AccountRepository repository) {
        return new DeleteAccount(repository);
    }
}
