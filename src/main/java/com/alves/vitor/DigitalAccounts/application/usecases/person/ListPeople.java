package com.alves.vitor.DigitalAccounts.application.usecases.person;

import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;

import java.util.List;

public class ListPeople {
    private final PersonRepository repository;

    public ListPeople(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public List<Person> findByName(String name) {
        return repository.findByName(name);
    }

    public Person findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    public List<Person> findByOcupation(String... ocupations) {
        return repository.findByOcupations(ocupations);
    }
}
