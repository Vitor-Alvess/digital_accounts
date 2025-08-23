package com.alves.vitor.DigitalAccounts.domain.enums;

public enum OperationType {
    DEPOSITO('D'),
    SAQUE('S');

    private char description;

    OperationType(char description) {
        this.description = description;
    }

    public char get() {
        return description;
    }
}
