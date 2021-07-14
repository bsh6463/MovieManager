package com.example.moviereview.movieList.service;

import com.example.moviereview.movieList.Movie;
import com.example.moviereview.movieList.comment.Comment;
import com.example.moviereview.movieList.repository.MovieRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieListServiceTest {


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieListService movieListService;

    @Autowired
    EntityManager entityManager;

    @Test
    void listTest(){

        makeMovie();

        movieListService.addToMovieList(new Movie());

        movieListService.getMovieList().forEach(System.out::println);

        movieListService.deleteMovie(1);

        movieListService.getMovieList().forEach(System.out::println);

    }

    public void makeMovie(){

        Movie movie1 = new Movie();
        movie1.setTitle("블랙위도우");
        movie1.setDirector("케이트 쇼트랜드");
        movie1.setActor("스칼렛요한슨");

        movieRepository.save(movie1);

        Movie movie2 = new Movie();
        movie2.setTitle("날씨의아이");
        movie2.setDirector("신카이 마코토");
        movie2.setActor("히나");

        movieRepository.save(movie2);

    }
}