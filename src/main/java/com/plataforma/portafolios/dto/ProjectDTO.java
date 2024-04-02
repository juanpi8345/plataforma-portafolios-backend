package com.plataforma.portafolios.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long projectId;
    @NotEmpty
    @Length(max = 100)
    private String name;
    @Lob
    @Length(max=800)
    private String description;
    @NotNull
    private LocalDate start;
    @NotNull
    private LocalDate end;
}
