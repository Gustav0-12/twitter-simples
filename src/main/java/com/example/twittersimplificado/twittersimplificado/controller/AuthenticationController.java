package com.example.twittersimplificado.twittersimplificado.controller;

import com.example.twittersimplificado.twittersimplificado.dto.AuthenticationDTO;
import com.example.twittersimplificado.twittersimplificado.dto.RegisterDTO;
import com.example.twittersimplificado.twittersimplificado.dto.UserResponseDTO;
import com.example.twittersimplificado.twittersimplificado.entities.User;
import com.example.twittersimplificado.twittersimplificado.exception.ResourceNotFound;
import com.example.twittersimplificado.twittersimplificado.exception.UniqueViolationException;
import com.example.twittersimplificado.twittersimplificado.repository.UserRepository;
import com.example.twittersimplificado.twittersimplificado.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {
        User user = userRepository.findByEmail(data.getEmail()).orElseThrow(() -> new ResourceNotFound("User not found"));

        if (passwordEncoder.matches(data.getPassword(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register (@RequestBody RegisterDTO data) {
       Optional<User> user = userRepository.findByEmail(data.getEmail());

        if (!user.isEmpty()) {
            throw new UniqueViolationException("Usúario já cadastrado");
        }

        User newUser = new User();
        newUser.setName(data.getName());
        newUser.setEmail(data.getEmail());
        newUser.setPassword(passwordEncoder.encode(data.getPassword()));
        newUser.setUserType(data.getUserType());

        userRepository.save(newUser);
        return ResponseEntity.ok(new UserResponseDTO(newUser.getId(), newUser.getName(), newUser.getEmail(), newUser.getUserType()));
    }
}
