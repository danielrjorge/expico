package com.drjenterprise.expico.entities.enums;

public enum BovineBreed {
    CHAROLES("Charoles"),
    ANGUS("Angus"),
    CRUZADO_ANGUS("Cruzado Angus");

    private final String stringValue;

    BovineBreed(String stringValue) {
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
