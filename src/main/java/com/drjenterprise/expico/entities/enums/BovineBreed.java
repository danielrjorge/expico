package com.drjenterprise.expico.entities.enums;

public enum BovineBreed {
    CHAROLES("Charoles"),
    ANGUS("Angus");

    private final String stringValue;

    BovineBreed(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
