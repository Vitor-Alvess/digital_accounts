package com.alves.vitor.DigitalAccounts.application.gateways;

import com.alves.vitor.DigitalAccounts.domain.entity.Operation;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository {
    List<Operation> findAll();

    List<Operation> findByType(char type);

    List<Operation> findByCpf(String cpf);

    List<Operation> findByDate(LocalDate date);

    List<Operation> findByInterval(LocalDate start, LocalDate end);

    Operation register(Operation operation);

    Operation update(Operation newOperation);
}
