package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {


    @Transactional
    @Query(value = "SELECT r from Review r, Book b WHERE b.bookid = :bookid")
    Iterable<Review> findReviewsById(Long bookid);
}
