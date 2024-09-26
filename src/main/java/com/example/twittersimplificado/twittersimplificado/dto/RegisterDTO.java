package com.example.twittersimplificado.twittersimplificado.dto;

import com.example.twittersimplificado.twittersimplificado.entities.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private UserType userType;
}
