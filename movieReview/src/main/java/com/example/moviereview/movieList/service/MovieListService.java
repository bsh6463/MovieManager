package com.example.moviereview.movieList.service;


import com.example.moviereview.movieList.dto.CommentDTO;
import com.example.moviereview.movieList.dto.MovieDTO;
import com.example.moviereview.movieList.movie.MovieEntity;
import com.example.moviereview.movieList.comment.CommentEntity;
import com.example.moviereview.movieList.repository.CommentRepository;
import com.example.moviereview.movieList.repository.MovieRepository;
import com.example.moviereview.naver.dto.SearchMovieReq;
import com.example.moviereview.naver.dto.SearchMovieRes;
import com.example.moviereview.naver.naverClient.NaverClient;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class MovieListService {

    private final MovieRepository movieRepository;
    private final NaverClient naverClient;
    private final CommentRepository commentRepository;
    private final EntityManager entityManager;


    //add to list
    public MovieDTO addToMovieList(MovieDTO movieDTO){

        var movieEntity = movieDTOToEntity(movieDTO);

        movieRepository.saveAndFlush(movieEntity);

        return movieDTO;

    }


    public List<MovieDTO> getMovieList() {


        var movieEntities= movieRepository.findAll();
        List<MovieDTO> movieDTOList = new ArrayList<>();

        for(int i = 0; i < movieEntities.size(); i++){

            movieDTOList.add(movieEntityToDTO(movieEntities.get(i)));

        }

        return movieDTOList;
    }

    public MovieDTO findMovieById(int id){

        var movieEntity = movieRepository.findById(id).get();
        return movieEntityToDTO(movieEntity);
    }

    public MovieDTO findMovieByTitle(String title) {

        var movieEntity = movieRepository.findByTitleContains(title);
        return movieEntityToDTO(movieEntity);
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

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(content);
        commentRepository.save(commentEntity);

        var movieEntity = movieRepository.findById(id).get();

        movieEntity.getCommentEntities().add(commentEntity);

        commentRepository.save(commentEntity);
        movieRepository.save(movieEntity);

        commentRepository.flush();
        movieRepository.flush();
    }

    @Transactional
    public void addCommentByTitle(String title, String content){

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(content);
        commentRepository.save(commentEntity);

        //var movieEntity = movieRepository.findByTitle(title);
        var movieEntity = movieRepository.findByTitleContains(title);
        movieEntity.getCommentEntities().add(commentEntity);

        commentRepository.save(commentEntity);
        movieRepository.save(movieEntity);

        commentRepository.flush();
        movieRepository.flush();

    }



    public void deleteComment(int id) {

        commentRepository.deleteById(id);
    }

    public MovieDTO movieEntityToDTO(MovieEntity movieEntity){

        var dto = new MovieDTO();
        dto.setTitle(movieEntity.getTitle());
        dto.setActor(movieEntity.getActor());
        dto.setDirector(movieEntity.getDirector());
        dto.setId(movieEntity.getId());
        dto.setImage(movieEntity.getImage());
        dto.setLink(movieEntity.getLink());
        dto.setSubtitle(movieEntity.getSubtitle());
        dto.setUserRating(movieEntity.getUserRating());
        dto.setCommentDTOs(commentEntityToDtoList(movieEntity.getCommentEntities()));

        return dto;
    }

    public MovieEntity movieDTOToEntity(MovieDTO movieDTO){

        var entity = new MovieEntity();
        entity.setTitle(movieDTO.getTitle());
        entity.setActor(movieDTO.getActor());
        entity.setDirector(movieDTO.getDirector());
        entity.setId(movieDTO.getId());
        entity.setImage(movieDTO.getImage());
        entity.setLink(movieDTO.getLink());
        entity.setSubtitle(movieDTO.getSubtitle());
        entity.setUserRating(movieDTO.getUserRating());
        entity.setCommentEntities(commentDtoToEntityList(movieDTO.getCommentDTOs()));

        return entity;

    }

    public CommentDTO commentEntityToDTO(CommentEntity commentEntity){

        var dto = new CommentDTO();
        dto.setContent(commentEntity.getContent());
        dto.setId(commentEntity.getId());

        return dto;
    }

    public CommentEntity commentDTOToEntity(CommentDTO commentDTO){

        var entity = new CommentEntity();
        entity.setContent(commentDTO.getContent());
        entity.setId(commentDTO.getId());

        return entity;
    }



    public List<CommentDTO> commentEntityToDtoList(List<CommentEntity> commentEntityList){

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for(int i=0;i<commentEntityList.size();i++){

            var temp = commentEntityList.get(i);
            commentDTOList.add(commentEntityToDTO(temp));
        }

        return commentDTOList;
    }

    public List<CommentEntity> commentDtoToEntityList(List<CommentDTO> commentDTOList){

        List<CommentEntity> commentEntityList = new ArrayList<>();

        for(int i=0;i<commentDTOList.size();i++){

            var temp = commentDTOList.get(i);
            commentEntityList.add(commentDTOToEntity(temp));
        }

        return commentEntityList;
    }



}
