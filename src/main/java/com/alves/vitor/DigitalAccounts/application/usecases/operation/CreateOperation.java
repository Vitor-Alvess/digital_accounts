package com.alves.vitor.DigitalAccounts.application.usecases.operation;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.application.gateways.OperationRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;
import com.alves.vitor.DigitalAccounts.domain.enums.OperationType;
import com.alves.vitor.DigitalAccounts.domain.exceptions.AccountNotFoundException;

import java.math.BigDecimal;

public class CreateOperation {
    private final OperationRepository operationRepository;
    private final AccountRepository accountRepository;

    public CreateOperation(OperationRepository operationRepository, AccountRepository accountRepository) {
        this.operationRepository = operationRepository;
        this.accountRepository = accountRepository;
    }

    public Operation create(Operation operation) {
        Account account = operation.getAccount();
        Account isPersisted = accountRepository.findByAgencyAndNumber(account.getAgency(), account.getNumber());

        if (isPersisted == null) {
            throw new AccountNotFoundException();
        }

        BigDecimal operationAmount = operation.getAmount();

        BigDecimal actualAmount = isPersisted.getTotalAmount();
        BigDecimal newAmount = actualAmount;
        if (operation.getType() == OperationType.DEPOSITO.get()) {
            newAmount = newAmount.add(operationAmount);
        }
        else {
            newAmount = newAmount.subtract(operationAmount);
        }

        isPersisted.setTotalAmount(newAmount);
        Account accountWithNewAmount = accountRepository.update(isPersisted);

        operation.setAccount(accountWithNewAmount);

        return operationRepository.register(operation);
    }
}
