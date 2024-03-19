package com.plataforma.portafolios.service;

import com.plataforma.portafolios.dto.AuthenticationRequest;
import com.plataforma.portafolios.dto.AuthenticationResponse;
import com.plataforma.portafolios.dto.UserDTO;
import com.plataforma.portafolios.exceptions.BadCredentialsException;
import com.plataforma.portafolios.exceptions.EntityAlreadyExists;
import com.plataforma.portafolios.exceptions.EntityNotFoundException;
import com.plataforma.portafolios.model.Employee;
import com.plataforma.portafolios.model.Employer;
import com.plataforma.portafolios.model.Profile;
import com.plataforma.portafolios.model.User;
import com.plataforma.portafolios.repository.IUserRepository;
import com.plataforma.portafolios.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse login(AuthenticationRequest request) throws EntityNotFoundException
                                    ,BadCredentialsException{
        UserDetails user=userRepository.findByUsername(request.getUsername());
        if(user == null)
            throw new EntityNotFoundException("That username does not exist");
        if(! passwordEncoder.matches(request.getPassword(),user.getPassword()))
            throw new BadCredentialsException("Bad credentials");
        String token=jwtService.getToken(user);
        return new AuthenticationResponse(token);
    }

    public void register(UserDTO user) throws EntityAlreadyExists {
        User userToRegister = new User();
        if(userRepository.findByUsername(user.getUsername()) != null)
            throw new EntityAlreadyExists("That username already exists");
        userToRegister.setUsername(user.getUsername());
        userToRegister.setEmail(user.getEmail());
        userToRegister.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == Role.EMPLOYEE) {
            userToRegister.setRole(Role.EMPLOYEE);
            Employee emp = new Employee();
            emp.setName(user.getUsername());
            userToRegister.setProfile((Profile) emp);
        }
        else{
            userToRegister.setRole(Role.EMPLOYER);
            Employer emp = new Employer();
            emp.setName(user.getUsername());
            userToRegister.setProfile((Profile) emp);
        }
        userRepository.save(userToRegister);

    }
}
