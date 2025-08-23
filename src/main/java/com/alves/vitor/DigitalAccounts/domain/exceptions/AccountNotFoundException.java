package com.alves.vitor.DigitalAccounts.domain.exceptions;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException() {
        super("Conta não encontrada");
    }
}
