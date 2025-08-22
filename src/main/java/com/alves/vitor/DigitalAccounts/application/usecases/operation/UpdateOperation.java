package com.alves.vitor.DigitalAccounts.application.usecases.operation;

import com.alves.vitor.DigitalAccounts.application.gateways.OperationRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;

public class UpdateOperation {
    private final OperationRepository repository;

    public UpdateOperation(OperationRepository repository) {
        this.repository = repository;
    }

    public Operation update(Operation newOperation) {
        return repository.update(newOperation);
    }
}
