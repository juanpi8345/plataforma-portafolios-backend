package com.plataforma.portafolios.model;

import jakarta.persistence.*;
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
    private String name;
    private byte[] image;
    private String description;
    private LocalDate start;
    private LocalDate end;
    @ManyToOne()
    @JoinColumn(name = "profileId")
    private Profile profile;
}
