package com.example.moviereview.controller;

import com.example.moviereview.movieList.Movie;
import com.example.moviereview.movieList.service.MovieListService;
import com.example.moviereview.naver.dto.SearchMovieRes;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@NoArgsConstructor
@SpringBootTest
class ApiControllerTest {

    private MovieListService movieListService;

    @Test
    @GetMapping("/search")
    public SearchMovieRes movieSearch(@RequestParam String query){

        return movieListService.searhMovie(query);

    }

    @Test
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie){

        return  movieListService.addToMovieList(movie);
    }


    @Test
    @GetMapping("/get")
    public List<Movie> getFindMovie(){

        return  movieListService.getMovieList();

    }

    @Test
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){

        movieListService.deleteMovie(id);

    }

}