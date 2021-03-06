package com.example.moviereview.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SearchMovieReq {


    private String query;
    private int display=10;
    private int start=1;
    //private String genre;
    //private String country;
   // private int yearfrom = 1000;
   // private int yearto = 2021;


    public MultiValueMap<String, String> makeQuery(){

        var multiValueMap = new LinkedMultiValueMap<String, String>();
        multiValueMap.add("query", query);
        multiValueMap.add("display", String.valueOf(display));
        multiValueMap.add("start", String.valueOf(start));
      //  multiValueMap.add("genre", genre);
     //   multiValueMap.add("country", country);
       // multiValueMap.add("yearfrom", String.valueOf(yearfrom));
      //  multiValueMap.add("yearto", String.valueOf(yearto));


        return multiValueMap;

    }



}
