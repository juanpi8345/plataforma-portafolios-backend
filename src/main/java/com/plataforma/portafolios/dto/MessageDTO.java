package com.plataforma.portafolios.dto;

import com.plataforma.portafolios.model.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter @Setter
@AllArgsConstructor
public class MessageDTO {
    private String firstProfileName;
    private String secondProfileName;
    private String message;
    private LocalDateTime date;
}
