package com.example.twittersimplificado.twittersimplificado.repository;

import com.example.twittersimplificado.twittersimplificado.entities.Tweets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweets, Long> {
}
