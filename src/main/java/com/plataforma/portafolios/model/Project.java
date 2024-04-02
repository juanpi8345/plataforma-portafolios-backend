package com.plataforma.portafolios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @NotEmpty
    @Length(max = 100)
    private String name;
    @Lob
    @Column(name = "image", columnDefinition="MEDIUMBLOB")
    @JsonIgnore
    private byte[] image;
    @NotEmpty
    @Lob
    @Length(max=800)
    private String description;
    @NotNull
    private LocalDate start;
    @NotNull
    private LocalDate end;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "profileId")
    private Employee employee;
}
