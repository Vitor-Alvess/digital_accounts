package com.alves.vitor.DigitalAccounts.domain.enums;

public enum OperationType {
    DEPOSIT("Depósito"),
    WITHDRAW("Saque");

    private String description;

    OperationType(String description) {
        this.description = description;
    }

    public String get() {
        return description;
    }
}
