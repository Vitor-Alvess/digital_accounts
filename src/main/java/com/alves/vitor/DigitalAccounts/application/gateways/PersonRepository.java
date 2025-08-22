package com.alves.vitor.DigitalAccounts.application.gateways;

import com.alves.vitor.DigitalAccounts.domain.entity.Person;

import java.util.List;

public interface PersonRepository {
    List<Person> findAll();

    List<Person> findByName(String name);

    Person findByCpf(String cpf);

    List<Person> findByOcupations(String... ocupations);

    Person register(Person person);

    Person update(String cpf, Person newPerson);

    Person delete(String cpf);
}
