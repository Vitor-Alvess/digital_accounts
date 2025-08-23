package com.alves.vitor.DigitalAccounts.infra.controller.mappers;

import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.AccountDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.AccountRequestCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.AccountRequestUpdateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.PersonAccountRequestCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.account.AccountResponseCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.account.AccountResponseDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toDomain(AccountDTO dto) {
        PersonDTO accountHolder = dto.getHolder();

        return new Account(
                dto.getAgency(),
                dto.getNumber(),
                dto.getType(),
                dto.getTotalAmount(),
                dto.getCurrency(),
                new Person(accountHolder.getCpf(), accountHolder.getName())
        );
    }

    public Account toDomain(AccountRequestCreateDTO dto) {
        PersonAccountRequestCreateDTO personDTO = dto.getAccountHolder();

        Person person = new Person(personDTO.getCpf(), personDTO.getName());

        return new Account(
                person,
                dto.getAccountType(),
                dto.getCurrency()

        );
    }

    public Account toDomain(String agency, String number, AccountRequestUpdateDTO requestBody) {
        return new Account(
                agency,
                number,
                requestBody.getAccountType(),
                requestBody.getAccountCurrency()
        );
    }

    public AccountDTO toDTO(Account domain) {
        Person accountHolder = domain.getHolder();

        return new AccountDTO(
                new PersonDTO(accountHolder.getName(), accountHolder.getCpf()),
                domain.getAgency(),
                domain.getNumber(),
                domain.getType(),
                domain.getCurrency(),
                domain.getTotalAmount()
        );
    }

    public AccountDTO toResponseCreateDTO(Account domain) {
        PersonDTO personDTO = new  PersonDTO(domain.getHolder().getName(), domain.getHolder().getCpf());

        return new AccountResponseCreateDTO(
                personDTO,
                domain.getAgency(),
                domain.getNumber(),
                domain.getType(),
                domain.getCurrency(),
                domain.getTotalAmount(),
                domain.getModifiedAt()
        );
    }

    public AccountDTO toResponseDeleteDTO(Account domain) {
        PersonDTO personDTO = new  PersonDTO(domain.getHolder().getName(), domain.getHolder().getCpf());

        return new AccountResponseDeleteDTO(
                personDTO,
                domain.getAgency(),
                domain.getNumber(),
                domain.getType(),
                domain.getCurrency(),
                domain.getTotalAmount(),
                domain.getModifiedAt()
        );
    }
}
