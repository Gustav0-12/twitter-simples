package com.example.twittersimplificado.twittersimplificado.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;



@Entity
@Table(name = "tb_tweets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Tweets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Instant creationTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
