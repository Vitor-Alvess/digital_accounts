package com.alves.vitor.DigitalAccounts.application.usecases.operation;

import com.alves.vitor.DigitalAccounts.application.gateways.OperationRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;

import java.time.LocalDate;
import java.util.List;

public class ListOperations {
    private final OperationRepository repository;

    public ListOperations(OperationRepository repository) {
        this.repository = repository;
    }

    public List<Operation> findAll() {
        return repository.findAll();
    }

    public List<Operation> findByType (char type) {
        return repository.findByType(type);
    }

    public List<Operation> findByCpf (String cpf) {
        return repository.findByCpf(cpf);
    }

    public List<Operation> findByDate (LocalDate date) {
        return repository.findByDate(date);
    }

    public List<Operation> findByInterval (LocalDate start, LocalDate end) {
        return repository.findByInterval(start, end);
    }

}
