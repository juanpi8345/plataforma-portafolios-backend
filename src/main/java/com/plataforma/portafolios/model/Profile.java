package com.plataforma.portafolios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;
    @NotNull
    private String name;
    @Lob
    @Column(name = "image", columnDefinition="MEDIUMBLOB")
    private byte[] image;
    @Lob
    @Length(max = 1000)
    private String description;
    private String occupations;
    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "profile_skills",
            joinColumns = @JoinColumn(name="profileId"),
            inverseJoinColumns = @JoinColumn(name="skillId")
    )
    private List<Skill> skills = new ArrayList<>();
}
