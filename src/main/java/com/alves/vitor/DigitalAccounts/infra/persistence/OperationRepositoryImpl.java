package com.alves.vitor.DigitalAccounts.infra.persistence;

import com.alves.vitor.DigitalAccounts.application.gateways.OperationRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class OperationRepositoryImpl implements OperationRepository {
    @Override
    public List<Operation> findAll() {
        return List.of();
    }

    @Override
    public List<Operation> findByType(char type) {
        return List.of();
    }

    @Override
    public List<Operation> findByCpf(String cpf) {
        return List.of();
    }

    @Override
    public List<Operation> findByDate(LocalDate date) {
        return List.of();
    }

    @Override
    public List<Operation> findByInterval(LocalDate start, LocalDate end) {
        return List.of();
    }

    @Override
    public Operation register(Operation operation) {
        return null;
    }

    @Override
    public Operation update(Operation newOperation) {
        return null;
    }
}
