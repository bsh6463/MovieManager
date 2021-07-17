package com.example.moviereview.movieList.dto;

import com.example.moviereview.movieList.comment.CommentEntity;
import com.example.moviereview.util.StatementCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private int id;

    private String title;

    private String message;

    private StatementCode statementCode;


    @Nullable
    private String link;
    @Nullable
    private String image;
    @Nullable
    private String subtitle;
    @Nullable
    private String director;
    @Nullable
    private String actor;
    @Nullable
    private String userRating;
    @Nullable
    private List<CommentDTO> commentDTOs = new ArrayList<>();

}
