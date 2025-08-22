package com.alves.vitor.DigitalAccounts.application.usecases.operation;

import com.alves.vitor.DigitalAccounts.application.gateways.OperationRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;

public class CreateOperation {
    private final OperationRepository repository;

    public CreateOperation(OperationRepository repository) {
        this.repository = repository;
    }

    public Operation create(Operation operation) {
        return repository.register(operation);
    }
}
