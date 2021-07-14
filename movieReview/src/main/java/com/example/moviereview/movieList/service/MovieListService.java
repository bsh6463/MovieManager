package com.example.moviereview.movieList.service;


import com.example.moviereview.movieList.Movie;
import com.example.moviereview.movieList.comment.Comment;
import com.example.moviereview.movieList.repository.CommentRepository;
import com.example.moviereview.movieList.repository.MovieRepository;
import com.example.moviereview.naver.dto.SearchMovieReq;
import com.example.moviereview.naver.dto.SearchMovieRes;
import com.example.moviereview.naver.naverClient.NaverClient;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class MovieListService {

    private final MovieRepository movieRepository;
    private final NaverClient naverClient;
    private final CommentRepository commentRepository;


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


    @Transactional
    public void addCommentById(int id, String content){

        Comment comment = new Comment();
        comment.setContent(content);


        Movie movie  = movieRepository.findById(id).get();


        movie.getComments().add(comment);

        commentRepository.save(comment);
        movieRepository.save(movie);

        commentRepository.flush();
        movieRepository.flush();
    }

    @Transactional
    public void addCommentByTitle(String title, String content){

        Comment comment = new Comment();
        comment.setContent(content);


        Movie movie  = movieRepository.findByTitle(title);


        movie.getComments().add(comment);

        commentRepository.save(comment);
        movieRepository.save(movie);

        commentRepository.flush();
        movieRepository.flush();
    }


    @Transactional
    public void addCommentByTitle2(String title, Comment comment){

        //Comment comment = new Comment();
        //comment.setContent(content);


        Movie movie  = movieRepository.findByTitle(title);


        movie.getComments().add(comment);

        commentRepository.save(comment);
        movieRepository.save(movie);

        commentRepository.flush();
        movieRepository.flush();
    }

    public void deleteComment(int id) {

        commentRepository.deleteById(id);
    }
}
