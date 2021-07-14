package com.example.moviereview.movieList.repository;

import com.example.moviereview.movieList.movie.MovieEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MovieEntityRepositoryTest {

    @Autowired
    MovieRepository movieRepository;


    @Test
    void repositoryTest(){

        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setActor("이지은");
        movieEntity.setDirector("봉준호");
        movieEntity.setSubtitle("미정");

        movieRepository.save(movieEntity);

        System.out.println(movieRepository.findAll());

        movieRepository.deleteById(1);

        System.out.println(movieRepository.findAll());

    }
}