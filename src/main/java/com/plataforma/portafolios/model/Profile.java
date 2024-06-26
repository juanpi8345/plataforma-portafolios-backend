package com.plataforma.portafolios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    @NotNull
    private String name;
    @Lob
    @Column(name = "image", columnDefinition="MEDIUMBLOB")
    @JsonIgnore
    private byte[] image;
    @Lob
    @Length(max = 1000)
    private String description;
    private String occupations;
}
