package com.alves.vitor.DigitalAccounts.application.usecases.person;

import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;
import com.alves.vitor.DigitalAccounts.domain.exceptions.CpfNotUniqueException;

public class CreatePerson {
    private final PersonRepository repository;
    
    public CreatePerson(PersonRepository repository) {
        this.repository = repository;
    }
    
    public Person create(Person person) {
        Person exists = repository.findByCpf(person.getCpf());

        if (exists != null) {
            throw new CpfNotUniqueException();
        }

        return repository.create(person);
    }
}
