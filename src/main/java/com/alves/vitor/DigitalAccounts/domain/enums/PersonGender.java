package com.alves.vitor.DigitalAccounts.domain.enums;

public enum PersonGender {
    MASCULINO('M'),
    FEMININO('F');

    private char description;

    PersonGender(char description) {
        this.description = description;
    }

    public char get() {
        return description;
    }
}
