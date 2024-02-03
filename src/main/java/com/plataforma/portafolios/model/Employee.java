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
@Table(name = "employees")
public class Employee extends Profile {
    // what are the employer searching
    private String occupations;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "employee_skills",
            joinColumns = @JoinColumn(name="profileId"),
            inverseJoinColumns = @JoinColumn(name="skillId")
    )
    private List<Skill> skills = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "employee")
    private List<Project> projects = new ArrayList<Project>();
}
