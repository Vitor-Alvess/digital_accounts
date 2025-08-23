package com.alves.vitor.DigitalAccounts.domain.enums;

public enum AccountCurrency {
    DOLAR('D'),
    REAL('R');

    private char value;

    AccountCurrency(char value) {
        this.value = value;
    }

    public char get() {
        return value;
    }
}
