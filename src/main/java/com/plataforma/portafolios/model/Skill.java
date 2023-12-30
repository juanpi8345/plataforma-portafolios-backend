package com.plataforma.portafolios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;
    @Lob
    @Column(name = "image", columnDefinition="MEDIUMBLOB")
    private byte[] image;
    @NotNull
    private String title;
    @JsonIgnore
    @ManyToMany(mappedBy = "skills")
    private List<Profile> profiles = new ArrayList<>();
}
