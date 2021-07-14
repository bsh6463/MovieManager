package com.example.moviereview.movieList.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String content;

    //movie와 comment는 1:N 관계
    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@ToString.Exclude
    //private Movie movie;
}
