package com.example.twittersimplificado.twittersimplificado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswordDTO {
    private Long id;
    private String oldPassword;
    private String newPassword;
    private String confirmationPassword;
}
