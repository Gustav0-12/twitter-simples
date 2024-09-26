package com.example.twittersimplificado.twittersimplificado.controller;

import com.example.twittersimplificado.twittersimplificado.dto.UserPasswordDTO;
import com.example.twittersimplificado.twittersimplificado.dto.UserResponseDTO;
import com.example.twittersimplificado.twittersimplificado.entities.User;
import com.example.twittersimplificado.twittersimplificado.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
       List<UserResponseDTO> user = userService.findAll();
       return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        UserResponseDTO user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/password/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody UserPasswordDTO data) {
        userService.updatePassword(id, data.getOldPassword(), data.getNewPassword(), data.getConfirmationPassword());
        return ResponseEntity.noContent().build();
    }
}
