package com.plataforma.portafolios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}
