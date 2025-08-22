package com.alves.vitor.DigitalAccounts.application.usecases.person;

import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;

public class CreatePerson {
    private final PersonRepository repository;
    
    public CreatePerson(PersonRepository repository) {
        this.repository = repository;
    }
    
    public Person create(Person person) {
        return repository.register(person);
    }
}
