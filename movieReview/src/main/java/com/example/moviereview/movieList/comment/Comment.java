package com.example.moviereview.movieList.comment;

import com.example.moviereview.movieList.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String content;

    //movie와 comment는 1:N 관계
    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@ToString.Exclude
    //private Movie movie;
}
