package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.repository.BookRepository;
import com.lambdaschool.starthere.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository repo;

    @Override
    public void save(Review review) {
        repo.save(review);
    }



    @Override
    public List<Review> findAll(Pageable pageable) {
        List<Review> bookList = new ArrayList<>();
        repo.findAll(pageable).iterator().forEachRemaining(bookList::add);
        return bookList;    }

    @Override
    public List<Review> findReviewsByBook(Pageable pageable, Long id) {
        List<Review> bookList = new ArrayList<>();
        repo.findReviewsById(id).iterator().forEachRemaining(bookList::add);
        return bookList;
    }
}
