package com.alves.vitor.DigitalAccounts.application.gateways;

import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;

import java.time.LocalDate;
import java.util.List;

public interface OperationRepository {

    List<Operation> findByType(char type);

    List<Operation> findByAccount(Account account);

    List<Operation> findByDate(LocalDate date);

    List<Operation> findByInterval(LocalDate start, LocalDate end);

    Operation register(Operation operation);
}
