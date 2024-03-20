package com.plataforma.portafolios.dto;

import com.plataforma.portafolios.util.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    private Role role;
    @NotNull
    private String password;
}
