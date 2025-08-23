package com.alves.vitor.DigitalAccounts.infra.controller;

import com.alves.vitor.DigitalAccounts.application.usecases.person.CreatePerson;
import com.alves.vitor.DigitalAccounts.application.usecases.person.DeletePerson;
import com.alves.vitor.DigitalAccounts.application.usecases.person.ListPeople;
import com.alves.vitor.DigitalAccounts.application.usecases.person.UpdatePerson;
import com.alves.vitor.DigitalAccounts.domain.entity.Person;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.PersonDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.PersonRequestUpdateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.mappers.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pessoa")
public class PersonController {
    private final PersonMapper mapper;

    private final ListPeople listPeople;
    private final CreatePerson createPerson;
    private final UpdatePerson updatePerson;
    private final DeletePerson deletePerson;

    @Autowired
    public PersonController(PersonMapper mapper, ListPeople listPeople, CreatePerson createPerson, UpdatePerson updatePerson, DeletePerson deletePerson) {
        this.mapper = mapper;
        this.listPeople = listPeople;
        this.createPerson = createPerson;
        this.updatePerson = updatePerson;
        this.deletePerson = deletePerson;
    }

    @GetMapping("/all")
    public List<PersonDTO> listAll() {
        return listPeople.findAll().stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/nome/{name}")
    public List<PersonDTO> listByName(@PathVariable String name) {
        return listPeople.findByName(name).stream().map(mapper::toDTO).toList();
    }

    @GetMapping("/cpf/{cpf}")
    public PersonDTO getByCpf(@PathVariable String cpf) {
        Person person = listPeople.findByCpf(cpf);
        return person != null ? mapper.toDTO(person) : null;
    }

    @GetMapping("/profissao/{ocupations}")
    public List<PersonDTO> listByOcupations(@PathVariable String ocupations) {
        return listPeople.findByOcupation(ocupations.split("&")).stream().map(mapper::toDTO).toList();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO personDTO) {
        Person created = createPerson.create(mapper.toDomain(personDTO));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getID())
                .toUri();

        return ResponseEntity.created(uri).body(mapper.toResponseCreationDTO(created));
    }

    @PutMapping("/atualizar/{cpf}")
    public PersonDTO update(@RequestBody PersonRequestUpdateDTO personDTO, @PathVariable String cpf) {
        Person newPerson = updatePerson.update(cpf, mapper.toDomain(cpf, personDTO));
        return mapper.toResponseUpdateDTO(newPerson);
    }

    @DeleteMapping("/deletar/{cpf}")
    public PersonDTO delete (@PathVariable String cpf) {
        Person deleted = deletePerson.delete(cpf);
        return mapper.toResponseDeleteDTO(deleted);
    }
}
