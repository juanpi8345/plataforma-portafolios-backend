package com.plataforma.portafolios.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.plataforma.portafolios.util.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long userId;
     @NotNull
     private String username;
     @Email
     private String email;
     @JsonIgnore
     @NotNull
     private String password;
     @JsonIgnore
     @Enumerated(EnumType.STRING)
     @NotNull
     private Role role;
     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @JoinColumn(name = "profileId")
     private Profile profile;


     @Override
     public Collection<? extends GrantedAuthority> getAuthorities() {
          List<GrantedAuthority> authorities = new ArrayList<>();
          authorities.add(new SimpleGrantedAuthority(role.name()));
          return authorities;
     }

     @Override
     public boolean isAccountNonExpired() {
          return true;
     }

     @Override
     public boolean isAccountNonLocked() {
          return true;
     }

     @Override
     public boolean isCredentialsNonExpired() {
          return true;
     }

     @Override
     public boolean isEnabled() {
          return true;
     }
}
