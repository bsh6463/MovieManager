package com.example.moviereview.movieList.repository;

import com.example.moviereview.movieList.movie.MovieEntity;
import com.example.moviereview.movieList.service.MovieListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CommentEntityRepositoryTest {


    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieListService movieListService;


    @Test
    @Transactional
    void commentTest(){

        makeMovie();
//
//        movieListService.addCommentById(1, "이게되나?");
//        movieListService.addCommentById(1, "이거도?");
//        movieListService.addCommentById(2, "날씨의아이 꿀잼");
//        movieListService.addCommentById(2, "너의이름은 보다 꿀잼");


        movieListService.addCommentByTitle("블랙위도우", "이게되나?");
        movieListService.addCommentByTitle("블랙위도우", "이거도?");
        movieListService.addCommentByTitle("날씨의아이", "날씨의아이 꿀잼");
        movieListService.addCommentByTitle("날씨의아이", "너의이름은 보다 꿀잼");


        movieRepository.findByTitle("블랙위도우").getCommentEntities().forEach(System.out::println);
        movieRepository.findByTitle("날씨의아이").getCommentEntities().forEach(System.out::println);

        System.out.println(movieRepository.findByTitle("날씨의아이"));


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