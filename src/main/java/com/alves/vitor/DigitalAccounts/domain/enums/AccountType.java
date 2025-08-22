package com.alves.vitor.DigitalAccounts.domain.enums;

public enum AccountType {
    SAVINGS("POUPANÇA"),
    CHECKINGS("CORRENTE");

    private String description;

    AccountType(String description) {
        this.description = description;
    }

    public String get() {
        return description;
    }
}
