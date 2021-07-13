package com.example.moviereview.controller;

import com.example.moviereview.movieList.Movie;
import com.example.moviereview.movieList.repository.MovieRepository;
import com.example.moviereview.naver.dto.SearchMovieReq;
import com.example.moviereview.naver.dto.SearchMovieRes;
import com.example.moviereview.naver.naverClient.NaverClient;
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
    private final NaverClient naverClient;

    @GetMapping("/search")
    public SearchMovieRes movieSearch(@RequestParam String query){

        System.out.println(query);

        SearchMovieReq searchMovieReq = new SearchMovieReq();
        searchMovieReq.setQuery(query);


        var result = naverClient.searchMovie(searchMovieReq);

        return result;
    }


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
