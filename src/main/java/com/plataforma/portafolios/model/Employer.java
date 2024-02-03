package com.plataforma.portafolios.model;

import com.plataforma.portafolios.util.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "employers")

public class Employer extends Profile {
    // what are the employer searching
    private String searching;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employer_searching_skills",
            joinColumns = @JoinColumn(name="profileId"),
            inverseJoinColumns = @JoinColumn(name="skillId")
    )
    private List<Skill> skillsSearched = new ArrayList<>();
}
