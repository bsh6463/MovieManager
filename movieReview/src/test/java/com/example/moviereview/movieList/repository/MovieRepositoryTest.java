package com.example.moviereview.movieList.repository;

import com.example.moviereview.movieList.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;


    @Test
    void repositoryTest(){

        Movie movie = new Movie();
        movie.setActor("이지은");
        movie.setDirector("봉준호");
        movie.setSubtitle("미정");

        movieRepository.save(movie);

        System.out.println(movieRepository.findAll());

        movieRepository.deleteById(1);

        System.out.println(movieRepository.findAll());

    }
}