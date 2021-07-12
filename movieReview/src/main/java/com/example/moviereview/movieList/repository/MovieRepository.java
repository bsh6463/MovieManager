package com.example.moviereview.movieList.repository;

import com.example.moviereview.movieList.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {


}
