package com.example.moviereview.movieList.repository;

import com.example.moviereview.movieList.movie.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<MovieEntity, Integer> {


    @Query(value = "select m from MovieEntity m where title = :title")
    MovieEntity findByTitle(@Param("title") String title);

    MovieEntity findByTitleContains(String title);

    Boolean existsByTitleContains(String title);
    Boolean existsByTitle(String title);


}
