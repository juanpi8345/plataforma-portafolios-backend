package com.plataforma.portafolios.exceptions;

import java.io.Serial;

public class EntitiesNotFoundException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public EntitiesNotFoundException(String message) {
        super(message);
    }
}
