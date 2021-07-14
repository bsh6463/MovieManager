package com.example.moviereview.controller;

import com.example.moviereview.movieList.dto.MovieDTO;
import com.example.moviereview.movieList.movie.MovieEntity;
import com.example.moviereview.movieList.service.MovieListService;
import com.example.moviereview.naver.dto.SearchMovieRes;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/find/id")
    public MovieDTO findMovieById(@RequestParam int id){

        return movieListService.findMovieById(id);
    }

    @GetMapping("/find/title")
    public MovieDTO findMovieByTitle(@RequestParam String title){

        return movieListService.findMovieByTitle(title);
    }

    @PostMapping("/comment/title")
    public void addCommentByTitle(@RequestParam String title, @RequestParam String content){

         movieListService.addCommentByTitle(title, content);

    }
    @PostMapping("/comment/id")
    public void addCommentByTitle(@RequestParam int id, @RequestParam String content){

        movieListService.addCommentById(id, content);

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

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable int id){

       movieListService.deleteMovie(id);

    }

    @DeleteMapping("/delete/comment/{id}")
    public void deleteComment(@PathVariable int id){

        movieListService.deleteComment(id);

    }
}
