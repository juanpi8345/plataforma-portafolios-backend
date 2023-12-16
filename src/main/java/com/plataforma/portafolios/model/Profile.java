package com.plataforma.portafolios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String name;
    private byte[] image;
    @OneToMany(mappedBy = "profile", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Project> projects;
    @ManyToMany()
    @JoinTable(
            name = "profile_skills",
            joinColumns = @JoinColumn(name="profileId"),
            inverseJoinColumns = @JoinColumn(name="skillId")
    )
    private List<Skill> skills;
}
