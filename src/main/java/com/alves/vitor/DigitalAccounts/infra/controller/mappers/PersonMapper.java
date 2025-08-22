package com.alves.vitor.DigitalAccounts.infra.controller.mappers;

import com.alves.vitor.DigitalAccounts.domain.entity.Person;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.PersonResponseCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.PersonResponseDeleteDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.PersonResponseUpdateDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public PersonDTO toDTO(Person domain) {
        return new PersonDTO(
                domain.getName(),
                domain.getCpf(),
                domain.getRg(),
                domain.getGender(),
                domain.getBirthDate(),
                domain.getOcupation()
        );
    }

    public Person toDomain(PersonDTO dto) {
        return new Person(
                dto.getCpf(),
                dto.getRg(),
                dto.getName(),
                dto.getGender(),
                dto.getBirthDate(),
                dto.getOcupation()
        );
    }

    public PersonResponseCreateDTO toResponseCreationDTO(Person domain) {
        return new PersonResponseCreateDTO(
                domain.getName(),
                domain.getCpf(),
                domain.getRg(),
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
