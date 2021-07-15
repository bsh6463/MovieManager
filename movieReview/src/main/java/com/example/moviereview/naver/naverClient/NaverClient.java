package com.example.moviereview.naver.naverClient;

import com.example.moviereview.naver.dto.SearchMovieReq;
import com.example.moviereview.naver.dto.SearchMovieRes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpRequest;
import java.time.LocalDate;


@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("${naver.url.search.movie}")
    private String naverMovieSearchUrl;


    public SearchMovieRes searchMovie(SearchMovieReq searchMovieReq){


        //url 생성
//        URI uri = UriComponentsBuilder.fromUriString(naverMovieSearchUrl)
//                .queryParams(searchMovieReq.makeQuery())
//                .build()
//                .encode()
//                .toUri();

        URI uri = UriComponentsBuilder
                .fromUriString(naverMovieSearchUrl)
                .queryParam("query", searchMovieReq.getQuery())
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("yearfrom", 1000)
                .queryParam("yearto", LocalDate.now().getYear())
                .build().encode().toUri();

        //JSON형태 header생성
        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);


        //HttpEntity

        //httpentity에 헤더
        var httpEntity = new HttpEntity<>(headers);

        var responseType = new ParameterizedTypeReference<SearchMovieRes>(){};


        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
               responseType
        );

        return responseEntity.getBody();

        }



    }


