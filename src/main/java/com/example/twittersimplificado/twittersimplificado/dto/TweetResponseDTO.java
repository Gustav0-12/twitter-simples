package com.example.twittersimplificado.twittersimplificado.dto;

import com.example.twittersimplificado.twittersimplificado.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponseDTO {
    private Long id;
    private String content;
    private Instant creationTime;
    private String username;
}
