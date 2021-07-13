package com.example.moviereview.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SearchMovieRes {

    private String lastBuildDate;

    private List<Item> items;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item{

        private String title;
        private String link;
        private String image;
        private String subtitle;
        private LocalDate pubDate;
        private String director;
        private String actor;
        private String userRating;

    }

}

