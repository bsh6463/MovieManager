package com.example.moviereview.controller;

import com.example.moviereview.movieList.Movie;
import com.example.moviereview.movieList.comment.Comment;
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

    @PostMapping("/comment")
    public void addComment(@RequestParam String title, @RequestParam String content){

         movieListService.addCommentByTitle(title, content);

    }



    @PostMapping("/add")
    public Movie addMovie(@RequestBody Movie movie){

        return  movieListService.addToMovieList(movie);
    }



    @GetMapping("/get")
    public List<Movie> findMovie(){

        var result = movieListService.getMovieList();
        result.forEach(System.out::println);
        return  result;


    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){

       movieListService.deleteMovie(id);

    }

    @DeleteMapping("/delete/comment/{id}")
    public void deleteComment(@PathVariable int id){

        movieListService.deleteComment(id);

    }
}
