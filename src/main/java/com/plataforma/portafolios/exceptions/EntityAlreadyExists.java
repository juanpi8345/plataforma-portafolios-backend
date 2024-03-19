package com.plataforma.portafolios.exceptions;

import java.io.Serial;

public class EntityAlreadyExists extends Exception {
    @Serial
    private static final long serialVersionUID = 4L;

    public EntityAlreadyExists(String message) {
        super(message);
    }
}
