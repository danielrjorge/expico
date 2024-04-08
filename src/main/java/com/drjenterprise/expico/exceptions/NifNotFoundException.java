package com.drjenterprise.expico.exceptions;

public class NifNotFoundException extends Exception{
    private static final String message = "This NIF doesn't associate with any user in the database.";
    public NifNotFoundException() {
        super(message);
    }
}
