package com.example.twittersimplificado.twittersimplificado.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TweetDTO {
    private Long userId;
    private String content;
}
