package com.alves.vitor.DigitalAccounts.application.usecases.person;

import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;

public class UpdatePerson {
    private final PersonRepository repository;

    public UpdatePerson(PersonRepository repository) {
     this.repository = repository;
    }

    public Person update(String cpf, Person newPerson) {
        return repository.update(cpf, newPerson);
    }
}
