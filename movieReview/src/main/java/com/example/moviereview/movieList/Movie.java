package com.example.moviereview.movieList;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie {

    @Id
    @GeneratedValue
    private int id;

    private String title;
    private String link;
    private String image;
    private String subtitle;
    private LocalDate pubDate;
    private String director;
    private String actor;
    private String userRating;

}
