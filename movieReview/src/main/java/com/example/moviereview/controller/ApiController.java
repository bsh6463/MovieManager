package com.example.moviereview.controller;

import com.example.moviereview.movieList.Movie;
import com.example.moviereview.movieList.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ApiController {

    private final MovieRepository movieRepository;

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie){

        movieRepository.save(movie);

        return movie;
    }

    @GetMapping("/get")
    public List<Movie> getFindAll(){

        var result = movieRepository.findAll();
        result.forEach(System.out::println);
        return result;

    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){

        movieRepository.deleteById(id);

    }
}
