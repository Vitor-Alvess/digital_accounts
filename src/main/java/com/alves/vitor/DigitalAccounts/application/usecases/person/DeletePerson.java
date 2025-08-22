package com.alves.vitor.DigitalAccounts.application.usecases.person;

import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;

public class DeletePerson {
    private final PersonRepository repository;

    public DeletePerson(PersonRepository repository) {
        this.repository = repository;
    }

    public Person delete(String cpf) {
        return repository.delete(cpf);
    }
}
