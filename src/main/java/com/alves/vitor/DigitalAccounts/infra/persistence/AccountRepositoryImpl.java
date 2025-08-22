package com.alves.vitor.DigitalAccounts.infra.persistence;

import com.alves.vitor.DigitalAccounts.application.gateways.AccountRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Account> accountRowMapper = (rs, rowNum) -> {
        return null;
    };

    @Autowired
    public AccountRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    @Override
    public List<Account> findByCpf(String cpf) {
        return List.of();
    }

    @Override
    public Account findByAgencyAndNumber(String agency, String number) {
        return null;
    }

    @Override
    public Account register(Account account) {
        return null;
    }

    @Override
    public Account update(Account newAccount) {
        return null;
    }

    @Override
    public Account delete(Account account) {
        return null;
    }
}
