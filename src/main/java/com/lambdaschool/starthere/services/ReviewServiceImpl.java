package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.repository.BookRepository;
import com.lambdaschool.starthere.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private BookRepository bookRepo;

    @Override
    public void save(Review review) {
        reviewRepo.save(review);
    }

    @Override
    public List<Review> findAll(Pageable pageable) {
        List<Review> bookList = new ArrayList<>();
        reviewRepo.findAll(pageable).iterator().forEachRemaining(bookList::add);
        return bookList;    }

    @Override
    public List<Review> findReviewsByBook(Pageable pageable, Long id) {
        List<Review> bookList = new ArrayList<>();
        reviewRepo.findReviewsById(id).iterator().forEachRemaining(bookList::add);
        return bookList;
    }

    @Transactional
    @Override
    public Review updateReview(Review review, long reviewid) {
        Review currentReview = reviewRepo.findById(reviewid).orElseThrow(EntityNotFoundException::new);
        if(review.getReview() != null){
            currentReview.setBook(review.getBook());
            currentReview.setRating(review.getRating());
            currentReview.setReview(review.getReview());
            currentReview.setUser(review.getUser());
        }
        reviewRepo.save(currentReview);
        return currentReview;
    }

    @Override
    public void delete(long reviewid) {
        reviewRepo.deleteById(reviewid);
    }

    @Override
    public void saveByBook(Review review, Long bookid) {
        Book book = bookRepo.findById(bookid).orElseThrow(EntityNotFoundException::new);
        review.setBook(book);
        book.getReviews().add(review);
        reviewRepo.save(review);
    }
}
