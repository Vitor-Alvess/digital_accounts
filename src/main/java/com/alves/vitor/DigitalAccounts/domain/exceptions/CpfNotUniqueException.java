package com.alves.vitor.DigitalAccounts.domain.exceptions;

public class CpfNotUniqueException extends RuntimeException {
    public CpfNotUniqueException() {
        super("Este cpf já está cadastrado!");
    }
}
