package com.example.twittersimplificado.twittersimplificado.controller;

import com.example.twittersimplificado.twittersimplificado.dto.TweetDTO;
import com.example.twittersimplificado.twittersimplificado.dto.TweetResponseDTO;
import com.example.twittersimplificado.twittersimplificado.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeline")
public class TweetController {

    @Autowired
    TweetService tweetService;

    @GetMapping
    public ResponseEntity<List<TweetResponseDTO>> findAll() {
        List<TweetResponseDTO> tweets = tweetService.findAll();
        return ResponseEntity.ok(tweets);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id) {
        tweetService.deleteTweet(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetResponseDTO> findById(@PathVariable Long id) {
        TweetResponseDTO tweet = tweetService.findById(id);
        return ResponseEntity.ok(tweet);
    }

    @PostMapping("/criar")
    public ResponseEntity<TweetResponseDTO> createTweet(@RequestBody TweetDTO data) {
       TweetResponseDTO tweet = tweetService.createTweet(data);
        return ResponseEntity.ok(tweet);
    }
}
