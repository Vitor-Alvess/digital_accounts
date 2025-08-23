package com.alves.vitor.DigitalAccounts.infra.controller;

import com.alves.vitor.DigitalAccounts.application.usecases.operation.CreateOperation;
import com.alves.vitor.DigitalAccounts.application.usecases.operation.ListOperations;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.domain.entity.Operation;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.OperationDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.OperationRequestCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.mappers.OperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/operacao")
public class OperationController {
    private final OperationMapper mapper;

    private final CreateOperation createOperation;
    private final ListOperations listOperations;

    @Autowired
    public OperationController(OperationMapper mapper, CreateOperation createOperation, ListOperations listOperations) {
        this.mapper = mapper;
        this.createOperation = createOperation;
        this.listOperations = listOperations;
    }

    @GetMapping("/tipo/{type}")
    public ResponseEntity<List<OperationDTO>> getByType(@PathVariable String type) {
        List<Operation> result = listOperations.findByType(type.toUpperCase().charAt(0));

        return ResponseEntity.ok(result.stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/conta/{agency}/{number}")
    public ResponseEntity<List<OperationDTO>> getByAgency(@PathVariable String agency, @PathVariable String number) {
        List<Operation> result = listOperations.findByAccount(new Account(agency, number));

        return ResponseEntity.ok(result.stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/data/{date}")
    public ResponseEntity<List<OperationDTO>> getByDate(@PathVariable LocalDate date) {
        List<Operation> result = listOperations.findByDate(date);

        return ResponseEntity.ok(result.stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/intervalo/{dates}")
    public ResponseEntity<List<OperationDTO>> getByInterval(@PathVariable String dates) {
        if (dates.contains(";") || dates.contains(",")) {
            throw new IllegalArgumentException("Para separar as datas a serem buscadas, utilise '&' entre elas");
        }

        List<Operation> result = listOperations.findByInterval(dates.split("&"));

        return ResponseEntity.ok(result.stream().map(mapper::toDTO).toList());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<OperationDTO> create(@RequestBody OperationRequestCreateDTO operation) {
        Operation created = createOperation.create(mapper.toDomain(operation));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(created.getID())
                .toUri();

        return ResponseEntity.created(uri).body(mapper.toDTO(created));
    }
}
