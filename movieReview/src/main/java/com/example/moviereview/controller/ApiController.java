package com.example.moviereview.controller;

import com.example.moviereview.movieList.dto.MovieDTO;
import com.example.moviereview.movieList.service.MovieListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {


    private final MovieListService movieListService;

    @GetMapping("/search")
    public MovieDTO movieSearch(@RequestParam String query){

       return movieListService.searchMovie(query);

    }

    @GetMapping("/find/id")
    public MovieDTO findMovieById(@RequestParam int id){

        return movieListService.findMovieById(id);
    }

    @GetMapping("/find/title")
    public MovieDTO findMovieByTitle(@RequestParam String title){

        return movieListService.findMovieByTitle(title);
    }

    @PostMapping("/comment/title")
    public MovieDTO addCommentByTitle(@RequestParam String title, @RequestParam String content){

         return movieListService.addCommentByTitle(title, content);

    }
    @PostMapping("/comment/id")
    public MovieDTO addCommentByTitle(@RequestParam int id, @RequestParam String content){

        return movieListService.addCommentById(id, content);

    }


    @PostMapping("/add")
    public MovieDTO addMovie(@RequestBody MovieDTO movieDTO){

        return  movieListService.addToMovieList(movieDTO);
    }



    @GetMapping("/get")
    public List<MovieDTO> findMovie(){

        var result = movieListService.getMovieList();
        result.forEach(System.out::println);
        return  result;


    }

    @DeleteMapping("/delete/movie/{id}")
    public void deleteMovie(@PathVariable int id){

       movieListService.deleteMovieWithId(id);

    }

    @DeleteMapping("/delete/comment/{id}")
    public void deleteComment(@PathVariable int id){

        movieListService.deleteComment(id);

    }
}
