package com.example.moviereview.controller;

import com.example.moviereview.movieList.dto.MovieDTO;
import com.example.moviereview.movieList.movie.MovieEntity;
import com.example.moviereview.movieList.repository.MovieRepository;
import com.example.moviereview.movieList.service.MovieListService;
import com.example.moviereview.naver.dto.SearchMovieRes;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@NoArgsConstructor
@SpringBootTest
class ApiControllerTest {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieListService movieListService;

    @Autowired
    EntityManager entityManager;


    @Test
    public SearchMovieRes movieSearch(@RequestParam String query){

        return movieListService.searhMovie(query);

    }

    @Test
    public MovieDTO addMovie(){

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle("날씨의 아이");
        movieDTO.setDirector("신카이 마코토");
        movieDTO.setActor("히나");

        return  movieListService.addToMovieList(movieDTO);
    }


    @Test
    public List<MovieDTO> getFindMovie(){

        return  movieListService.getMovieList();

    }

    @Test
    public void deleteMovie(@PathVariable int id){

        movieListService.deleteMovie(id);

    }

    @Test
    public void addAndCommentTest(){

        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle("날씨의 아이");
        movieDTO.setDirector("신카이 마코토");
        movieDTO.setActor("히나");
        movieListService.addToMovieList(movieDTO);

        String content = "될까..?";

        movieListService.addCommentByTitle("날씨의 아이", "될까?");



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