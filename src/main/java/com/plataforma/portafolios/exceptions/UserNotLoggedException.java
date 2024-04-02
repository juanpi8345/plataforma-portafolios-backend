package com.plataforma.portafolios.exceptions;

import java.io.Serial;

public class UserNotLoggedException extends Exception{
    @Serial
    private static final long serialVersionUID = 6L;

    public UserNotLoggedException(String message) {
        super(message);
    }
}
