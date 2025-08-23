package com.alves.vitor.DigitalAccounts.config;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.application.gateways.OperationRepository;
import com.alves.vitor.DigitalAccounts.application.usecases.account.ListAccounts;
import com.alves.vitor.DigitalAccounts.application.usecases.operation.CreateOperation;
import com.alves.vitor.DigitalAccounts.application.usecases.operation.ListOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperationBeansConfig {

    @Bean
    ListOperations listOperations(OperationRepository operationRepository) {
        return new ListOperations(operationRepository);
    }

    @Bean
    CreateOperation createOperation(OperationRepository operationRepository, AccountRepository accountRepository) {
        return new CreateOperation(operationRepository, accountRepository);
    }
}
