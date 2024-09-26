package com.example.twittersimplificado.twittersimplificado.service;

import com.example.twittersimplificado.twittersimplificado.dto.TweetDTO;
import com.example.twittersimplificado.twittersimplificado.dto.TweetResponseDTO;
import com.example.twittersimplificado.twittersimplificado.entities.Tweets;
import com.example.twittersimplificado.twittersimplificado.entities.User;
import com.example.twittersimplificado.twittersimplificado.exception.ResourceNotFound;
import com.example.twittersimplificado.twittersimplificado.exception.UnauthorizedUserException;
import com.example.twittersimplificado.twittersimplificado.repository.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TweetService {

    @Autowired
    TweetRepository repository;

    public List<TweetResponseDTO> findAll() {
        List<Tweets> tweets = repository.findAll();
        return tweets.stream().map(tweet -> new TweetResponseDTO(tweet.getId(), tweet.getContent(), tweet.getCreationTime(), tweet.getUser().getName())).collect(Collectors.toList());
    }

    public TweetResponseDTO findById(Long id) {
        Tweets tweets = repository.findById(id).orElseThrow(() -> new ResourceNotFound("Tweet não encontrado"));
        return new TweetResponseDTO(tweets.getId(), tweets.getContent(), tweets.getCreationTime(), tweets.getUser().getName());
    }

    public void verifyUserAuthorization(Tweets tweet, User user) {
        if (!tweet.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedUserException("Usúario não autorizado");
        }
    }

    public void deleteTweet(Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tweets tweet = repository.findById(id).orElseThrow(() ->  new ResourceNotFound("Tweet não encontrado"));

        verifyUserAuthorization(tweet, user);

        repository.deleteById(id);
    }

    public TweetResponseDTO createTweet(TweetDTO data) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Tweets newTweet = new Tweets();
        newTweet.setContent(data.getContent());
        newTweet.setCreationTime(Instant.now());
        newTweet.setUser(user);
        repository.save(newTweet);
        return new TweetResponseDTO(newTweet.getId(), newTweet.getContent(),newTweet.getCreationTime(), user.getName());
    }
}
