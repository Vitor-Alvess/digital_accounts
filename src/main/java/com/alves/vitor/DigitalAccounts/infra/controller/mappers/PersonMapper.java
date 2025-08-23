package com.alves.vitor.DigitalAccounts.infra.controller.mappers;

import com.alves.vitor.DigitalAccounts.domain.entity.Person;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.PersonRequestUpdateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.person.PersonResponseCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.person.PersonResponseDeleteDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.person.PersonResponseUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toDomain(PersonDTO dto) {
        return new Person(
                dto.getCpf(),
                dto.getName(),
                dto.getGender(),
                dto.getBirthDate(),
                dto.getOcupation()
        );
    }

    public Person toDomain(String cpf, PersonRequestUpdateDTO dto) {
        return new Person(
                cpf,
                dto.getName(),
                dto.getGender(),
                dto.getOcupation()
        );
    }

    public PersonDTO toDTO(Person domain) {
        return new PersonDTO(
                domain.getName(),
                domain.getCpf(),
                domain.getGender(),
                domain.getBirthDate(),
                domain.getOcupation()
        );
    }

    public PersonResponseCreateDTO toResponseCreationDTO(Person domain) {
        return new PersonResponseCreateDTO(
                domain.getName(),
                domain.getCpf(),
                domain.getModifiedAt()
        );
    }

    public PersonResponseDeleteDTO toResponseDeleteDTO(Person domain) {
        return new PersonResponseDeleteDTO(
                domain.getName(),
                domain.getCpf(),
                domain.getModifiedAt()
        );
    }

    public PersonResponseUpdateDTO toResponseUpdateDTO(Person domain) {
        return new PersonResponseUpdateDTO(
                domain.getName(),
                domain.getCpf(),
                domain.getGender(),
                domain.getOcupation()
        );
    }
}
