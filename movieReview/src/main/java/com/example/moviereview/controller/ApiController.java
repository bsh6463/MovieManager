package com.example.moviereview.controller;

import com.example.moviereview.movieList.Movie;
import com.example.moviereview.movieList.service.MovieListService;
import com.example.moviereview.naver.dto.SearchMovieRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {


    private final MovieListService movieListService;

    @GetMapping("/search")
    public SearchMovieRes movieSearch(@RequestParam String query){

       return movieListService.searhMovie(query);

    }


    @PostMapping
    public Movie addMovie(@RequestBody Movie movie){

        return  movieListService.addToMovieList(movie);
    }



    @GetMapping("/get")
    public List<Movie> getFindMovie(){

        return  movieListService.getMovieList();

    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){

       movieListService.deleteMovie(id);

    }
}
