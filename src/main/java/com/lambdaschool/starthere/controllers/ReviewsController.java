package com.lambdaschool.starthere.controllers;


import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.services.BookService;
import com.lambdaschool.starthere.services.ReviewService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reviews")
public class ReviewsController {

    private static final Logger logger = LoggerFactory.getLogger(Review.class);

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private BookService bookService;


    @ApiOperation(value = "return a list of Reviews, supports pagination", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "specifies the page that you want to access"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "specifies the page size"),
            @ApiImplicitParam(name = "sort", dataType = "string", allowMultiple = true, paramType = "query", value = "Sorts result [name, address, etc]")
    })
    @GetMapping(value = "/reviews", produces = {"application/json"})
    public ResponseEntity<?> listAllReviews(@PageableDefault(page = 0, size = 10) Pageable pageable)
    {

        List<Review> myReviews = reviewService.findAll(pageable);

        if (myReviews == null) {
            throw new ResourceNotFoundException("no reviews found");
        } else{
            logger.info("/reviews/reviews accessed");
        }

        return new ResponseEntity<>(myReviews, HttpStatus.OK);
    }


    @ApiOperation(value = "return a list of Reviews given a bookid, supports pagination", response = Book.class, responseContainer = "List")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "specifies the page that you want to access"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "specifies the page size"),
            @ApiImplicitParam(name = "sort", dataType = "string", allowMultiple = true, paramType = "query", value = "Sorts result [name, address, etc]")
    })
    @GetMapping(value = "/bybook/{bookid}", produces = {"application/json"})
    public ResponseEntity<?> listReviewsByBook(@PathVariable long bookid, @PageableDefault(page = 0, size = 10) Pageable pageable)
    {

        List<Review> myReviews = reviewService.findReviewsByBook(pageable, bookid);

        if (myReviews == null) {
            throw new ResourceNotFoundException("no reviews found for this book");
        } else{
            logger.info("/reviews/books/{bookid} GET accessed");
        }

        return new ResponseEntity<>(myReviews, HttpStatus.OK);
    }

    @PutMapping(value = "/byreview/{reviewid}")
    public ResponseEntity<?> editReview(@PathVariable long reviewid, @RequestBody Review review) {
        reviewService.updateReview(review, reviewid);
        logger.info("/reviews/byreview/{reviewid} PUT accessed");
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @ApiOperation(value = "adds a review to a book", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "review successfully added", response = Review.class),
            @ApiResponse(code = 500, message = "failed to add review", response = ErrorDetail.class)
    })
    @PostMapping(value = "/add}")
    public ResponseEntity<?> postReview(@RequestBody Review review) {


        reviewService.save(review);

        logger.info("/reviews/add POST endpoint accessed");

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
