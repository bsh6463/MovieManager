package com.example.moviereview.movieList.service;


import com.example.moviereview.movieList.Movie;
import com.example.moviereview.movieList.repository.MovieRepository;
import com.example.moviereview.naver.dto.SearchMovieReq;
import com.example.moviereview.naver.dto.SearchMovieRes;
import com.example.moviereview.naver.naverClient.NaverClient;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class MovieListService {

    private final MovieRepository movieRepository;
    private final NaverClient naverClient;

    //add to list
    public Movie addToMovieList(Movie movie){

        movieRepository.save(movie);

        return movie;

    }


    public List<Movie> getMovieList() {


        return movieRepository.findAll();

    }

    public SearchMovieRes searhMovie(String query){

        SearchMovieReq searchMovieReq = new SearchMovieReq();
        searchMovieReq.setQuery(query);

        return naverClient.searchMovie(searchMovieReq);

    }

    public void deleteMovie(int id){

        movieRepository.deleteById(id);
    }

}
