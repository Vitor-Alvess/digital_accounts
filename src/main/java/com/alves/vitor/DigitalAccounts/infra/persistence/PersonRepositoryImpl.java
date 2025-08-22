package com.alves.vitor.DigitalAccounts.infra.persistence;

import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonRepositoryImpl implements PersonRepository {
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> findAll() {
        return List.of();
    }

    @Override
    public List<Person> findByName(String name) {
        return List.of();
    }

    @Override
    public Person findByCpf(String cpf) {
        return null;
    }

    @Override
    public List<Person> findByOcupations(String... ocupations) {
        return List.of();
    }

    @Override
    public Person register(Person person) {
        return null;
    }

    @Override
    public Person update(String cpf, Person newPerson) {
        return null;
    }

    @Override
    public Person delete(String cpf) {
        return null;
    }
}
