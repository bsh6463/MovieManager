package com.example.moviereview.movieList.movie;


import com.example.moviereview.movieList.comment.CommentEntity;
import com.example.moviereview.util.StateCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //private String message;
    //private StateCode stateCode;

    private String title;
    @Nullable
    private String link;
    @Nullable
    private String image;
    @Nullable
    private String subtitle;
    @Nullable
    private LocalDate pubDate;
    @Nullable
    private String director;
    @Nullable
    private String actor;
    @Nullable
    private String userRating;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private List<CommentEntity> commentEntities = new ArrayList<>();

}
