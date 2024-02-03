package com.plataforma.portafolios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plataforma.portafolios.util.Profile;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @NotNull
    private String name;
    @Lob
    @Column(name = "image", columnDefinition="MEDIUMBLOB")
    private byte[] image;
    private String description;
    private LocalDate start;
    private LocalDate end;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "profileId")
    private Employee employee;
}
