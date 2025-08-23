package com.alves.vitor.DigitalAccounts.application.usecases.operation;

import com.alves.vitor.DigitalAccounts.application.gateways.OperationRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;

import java.time.LocalDate;
import java.util.List;

public class ListOperations {
    private final OperationRepository repository;

    public ListOperations(OperationRepository repository) {
        this.repository = repository;
    }

    public List<Operation> findByType (char type) {
        return repository.findByType(type);
    }

    public List<Operation> findByAccount (Account account) {
        return repository.findByAccount(account);
    }

    public List<Operation> findByDate (LocalDate date) {
        return repository.findByDate(date);
    }

    public List<Operation> findByInterval (String... intervals) {
        LocalDate start = LocalDate.parse(intervals[0]);
        LocalDate end = LocalDate.parse(intervals[1]);

        return repository.findByInterval(start, end);
    }

}
