package com.example.moviereview.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchImageRes {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchImageItems> items;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchImageItems{

        private String title;
        private String link;
        private String thumbnail;
        private String sizeheight;
        private String sizewidth;


    }
}
