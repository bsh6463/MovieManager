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
import com.example.moviereview.util.StatementCode;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Slf4j
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

        if(movieEntities.stream().findFirst().isPresent()){

            List<MovieDTO> movieDTOList = new ArrayList<>();

            for(int i = 0; i < movieEntities.size(); i++){

                movieDTOList.add(movieEntityToDTO(movieEntities.get(i)));

            }

            return movieDTOList;

        }else { //null인경우

            List<MovieDTO> movieDTOList = new ArrayList<>();
            var movieDTO = new MovieDTO();
            movieDTO.setStatementCode(StatementCode.SC_NoResult);
            movieDTOList.add(movieDTO);
            return  movieDTOList;
        }

    }

    public MovieDTO findMovieById(int id){

        var tempMovieEntity = movieRepository.findById(id);
        if(tempMovieEntity.isPresent()){
            var movieEntity = tempMovieEntity.get();

            return movieEntityToDTO(movieEntity);
        }
        else {

            return noSearchResultInfoId(id);
        }

    }

    public MovieDTO findMovieByTitle(String title) {

        var movieEntity = movieRepository.findByTitleContains(title);


        if(movieEntity != null){
            return movieEntityToDTO(movieEntity);
        }
        else {

            return noSearchResultInfoTitle(title);
        }

    }


    public MovieDTO searchMovie(String query){

        SearchMovieReq searchMovieReq = new SearchMovieReq();
        searchMovieReq.setQuery(query);

        SearchMovieRes searchMovieRes = naverClient.searchMovie(searchMovieReq);


        if(searchMovieRes.getTotal() > 0){
            //naver 영화 검색 결과가 존재
            MovieDTO movieDTO = new MovieDTO();

            var temp = searchMovieRes.getItems().stream().findFirst();

            if(temp.isPresent()) {

                movieDTO.setTitle(temp.get().getTitle());
                movieDTO.setActor(temp.get().getActor());
                movieDTO.setDirector(temp.get().getDirector());
                movieDTO.setLink(temp.get().getLink());
                movieDTO.setSubtitle(temp.get().getSubtitle());
                movieDTO.setImage(temp.get().getImage());
                movieDTO.setUserRating(temp.get().getUserRating());

            }
            return movieDTO;
        }
        else{
            //naver 영화 검색 결과가 없음
            log.info("{} 의 Naver 영화검색 결과가 없습니다.",query);
            MovieDTO movieDTO = new MovieDTO();
            movieDTO.setStatementCode(StatementCode.SC_NoResult);

            return  movieDTO;
        }
    }

    public void deleteMovieWithId(int id) {

        movieRepository.deleteById(id);
    }


    @Transactional
    public MovieDTO addCommentById(int id, String content){

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(content);
        commentRepository.save(commentEntity);

        var tempMovieEntity = movieRepository.findById(id);

        if(tempMovieEntity.isPresent()){
            var movieEntity = tempMovieEntity.get();
            movieEntity.getCommentEntities().add(commentEntity);

            commentRepository.save(commentEntity);
            movieRepository.save(movieEntity);

            commentRepository.flush();
            movieRepository.flush();

            return movieEntityToDTO(movieEntity);
        }
        else {

            return noSearchResultInfoId(id);
        }



    }

    @Transactional
    public MovieDTO addCommentByTitle(String title, String content){

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setContent(content);
        commentRepository.save(commentEntity);


        var movieEntity = movieRepository.findByTitleContains(title);

        if(movieEntity != null){

            movieEntity.getCommentEntities().add(commentEntity);

            commentRepository.save(commentEntity);
            movieRepository.save(movieEntity);

            commentRepository.flush();
            movieRepository.flush();

            return movieEntityToDTO(movieEntity);

        }
        else {

           return noSearchResultInfoTitle(title);
        }

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
        if(movieDTO.getCommentDTOs() != null){
            entity.setCommentEntities(commentDtoToEntityList(movieDTO.getCommentDTOs()));
        }
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


    public MovieDTO noSearchResultInfoId(int id){
        log.info("id : {} 인 Movie/Comment가 DB에 없습니다.", id);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setStatementCode(StatementCode.SC_NoResult);
        return movieDTO;
    }

    public MovieDTO noSearchResultInfoTitle(String title){
        log.info("제목 : {}인 Movie가 DB에 없습니다.", title);
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setStatementCode(StatementCode.SC_NoResult);
        return movieDTO;
    }

}
