package com.alves.vitor.DigitalAccounts.config;

import com.alves.vitor.DigitalAccounts.application.gateways.PersonRepository;
import com.alves.vitor.DigitalAccounts.application.usecases.person.CreatePerson;
import com.alves.vitor.DigitalAccounts.application.usecases.person.DeletePerson;
import com.alves.vitor.DigitalAccounts.application.usecases.person.ListPeople;
import com.alves.vitor.DigitalAccounts.application.usecases.person.UpdatePerson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonBeansConfig {

    @Bean
    ListPeople listPeople(PersonRepository repository) {
        return new ListPeople(repository);
    }

    @Bean
    CreatePerson createPerson(PersonRepository repository) {
        return new CreatePerson(repository);
    }

    @Bean
    UpdatePerson updatePerson(PersonRepository repository) {
        return new UpdatePerson(repository);
    }

    @Bean
    DeletePerson deletePerson(PersonRepository repository) {
        return new DeletePerson(repository);
    }
}
