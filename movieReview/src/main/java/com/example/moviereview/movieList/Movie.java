package com.example.moviereview.movieList;


import com.example.moviereview.movieList.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String title;
    private String link;
    private String image;
    private String subtitle;
    private LocalDate pubDate;
    private String director;
    private String actor;
    private String userRating;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @ToString.Exclude
    private List<Comment> comments = new ArrayList<>();

}
