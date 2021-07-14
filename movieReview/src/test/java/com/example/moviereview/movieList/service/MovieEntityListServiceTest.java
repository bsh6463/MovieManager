package com.example.moviereview.movieList.service;

import com.example.moviereview.movieList.dto.MovieDTO;
import com.example.moviereview.movieList.movie.MovieEntity;
import com.example.moviereview.movieList.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

@SpringBootTest
class MovieEntityListServiceTest {


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieListService movieListService;

    @Autowired
    EntityManager entityManager;

    @Test
    void listTest(){

        makeMovie();

        movieListService.addToMovieList(new MovieDTO());

        movieListService.getMovieList().forEach(System.out::println);

        movieListService.deleteMovie(1);

        movieListService.getMovieList().forEach(System.out::println);

    }

    public void makeMovie(){

        MovieEntity movieEntity1 = new MovieEntity();
        movieEntity1.setTitle("블랙위도우");
        movieEntity1.setDirector("케이트 쇼트랜드");
        movieEntity1.setActor("스칼렛요한슨");

        movieRepository.save(movieEntity1);

        MovieEntity movieEntity2 = new MovieEntity();
        movieEntity2.setTitle("날씨의아이");
        movieEntity2.setDirector("신카이 마코토");
        movieEntity2.setActor("히나");

        movieRepository.save(movieEntity2);

    }
}