package com.plataforma.portafolios.exceptions;

import java.io.Serial;

public class InvalidEntityException extends Exception {
    @Serial
    private static final long serialVersionUID = 5L;

    public InvalidEntityException(String message) {
        super(message);
    }
}
