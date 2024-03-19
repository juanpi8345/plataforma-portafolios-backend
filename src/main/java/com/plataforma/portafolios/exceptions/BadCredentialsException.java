package com.plataforma.portafolios.exceptions;

import java.io.Serial;

public class BadCredentialsException extends Exception{
    @Serial
    private static final long serialVersionUID = 3L;

    public BadCredentialsException(String message) {
        super(message);
    }
}
