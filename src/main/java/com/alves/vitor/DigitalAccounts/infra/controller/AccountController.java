package com.alves.vitor.DigitalAccounts.infra.controller;

import com.alves.vitor.DigitalAccounts.application.usecases.account.CreateAccount;
import com.alves.vitor.DigitalAccounts.application.usecases.account.DeleteAccount;
import com.alves.vitor.DigitalAccounts.application.usecases.account.ListAccounts;
import com.alves.vitor.DigitalAccounts.application.usecases.account.UpdateAccount;
import com.alves.vitor.DigitalAccounts.domain.entity.Account;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.AccountDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.AccountRequestCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.request.AccountRequestUpdateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.account.AccountResponseCreateDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.dto.response.account.AccountResponseDeleteDTO;
import com.alves.vitor.DigitalAccounts.infra.controller.mappers.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class AccountController {
    private final AccountMapper mapper;

    private final ListAccounts listAccounts;
    private final CreateAccount createAccount;
    private final UpdateAccount updateAccount;
    private final DeleteAccount deleteAccount;

    @Autowired
    public AccountController(AccountMapper mapper, ListAccounts listAccounts, CreateAccount createAccount, UpdateAccount updateAccount, DeleteAccount deleteAccount) {
        this.mapper = mapper;
        this.listAccounts = listAccounts;
        this.createAccount = createAccount;
        this.updateAccount = updateAccount;
        this.deleteAccount = deleteAccount;
    }

    @GetMapping("/{agency}")
    public ResponseEntity<List<AccountDTO>> findByAgency(@PathVariable String agency) {
        List<Account> accountsList = listAccounts.findByAgency(agency);
        return ResponseEntity.ok(accountsList.stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<List<AccountDTO>> listAccountsByCpf(@PathVariable String cpf) {
        List<Account> accountsList = listAccounts.findByCpf(cpf);
        return ResponseEntity.ok(accountsList.stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/{agency}/{number}")
    public ResponseEntity<AccountDTO> getByAgencyAndNumber(@PathVariable String agency, @PathVariable String number) {
        Account account = listAccounts.findByAgencyAndNumber(agency, number);
        return ResponseEntity.ok(mapper.toDTO(account));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<AccountResponseCreateDTO> create(@RequestBody AccountRequestCreateDTO dto) {
        Account newAccount = createAccount.create(mapper.toDomain(dto));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(newAccount.getID())
                .toUri();

        return ResponseEntity.created(uri).body(mapper.toResponseCreateDTO(newAccount));
    }

    @PutMapping("/atualizar/{agency}/{number}")
    public ResponseEntity<AccountDTO> update(@RequestBody AccountRequestUpdateDTO body, @PathVariable String agency, @PathVariable String number) {
        Account updated = updateAccount.update(mapper.toDomain(agency, number, body));

        return ResponseEntity.ok(mapper.toDTO(updated));
    }

    @DeleteMapping("/deletar/{agency}/{number}")
    public ResponseEntity<AccountResponseDeleteDTO> delete(@PathVariable String agency, @PathVariable String number) {
        Account deleted = deleteAccount.delete(agency, number);

        return ResponseEntity.ok(mapper.toResponseDeleteDTO(deleted));
    }
}
