package com.example.moviereview.movieList.repository;

import com.example.moviereview.movieList.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
