package com.plataforma.portafolios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class AuthenticationResponse {
    private String jwt;
}
