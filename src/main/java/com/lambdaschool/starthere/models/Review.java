package com.lambdaschool.starthere.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewid;

    @Column
    private String review;

    @Column
    private int rating; //0-5

    @Column(nullable = false)
    private String user;

    @ManyToOne
    @JsonIgnoreProperties("reviews")
    private Book book;




    public Review() {
    }

    public Review(String review, int rating, String user, Book book) {
        this.review = review;
        this.rating = rating;
        this.user = user;
        this.book = book;
    }

    public long getReviewid() {
        return reviewid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


}
