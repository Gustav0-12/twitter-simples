package com.example.twittersimplificado.twittersimplificado.service;

import com.example.twittersimplificado.twittersimplificado.dto.UserResponseDTO;
import com.example.twittersimplificado.twittersimplificado.entities.User;
import com.example.twittersimplificado.twittersimplificado.exception.InvalidPasswordException;
import com.example.twittersimplificado.twittersimplificado.exception.ResourceNotFound;
import com.example.twittersimplificado.twittersimplificado.exception.UnauthorizedUserException;
import com.example.twittersimplificado.twittersimplificado.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserResponseDTO> findAll() {
        List<User> users = repository.findAll();
        return users.stream().map(user -> new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserType()))
                .collect(Collectors.toList());
    }

    public UserResponseDTO findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Usúario não encontrado"));
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserType());
    }

    public void verifyUserAuthorization(User user) {
        User authenticated = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!user.getId().equals(authenticated.getId())) {
            throw new UnauthorizedUserException("Usúario não autorizado");
        }
    }

    public User updatePassword(Long id, String oldPassword, String newPassword, String confirmationPassword) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Usúario não encontrado"));

        verifyUserAuthorization(user);

        if(!passwordEncoder.matches(oldPassword, user.getPassword())) {
                throw new InvalidPasswordException("Senha incorreta");
        }

        if (!newPassword.equals(confirmationPassword)) {
            throw new InvalidPasswordException("Nova senha não confere com a senha de confirmação");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return repository.save(user);
    }
}
