package com.lambdaschool.starthere.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;


    @Column(nullable = false)
    private String booktitle;
    @Column
    private String publisher;
    @Column
    private String url;
    @Column
    private String imageurl;
    @Column
    private String license;
    @Column
    private String author;


    @OneToMany
    @JsonIgnoreProperties("book")
    private List<Review> reviews = new ArrayList<>();

    public Book() {
    }


    public Book(String booktitle, String publisher, String url, String imageurl, String license, String author, List<Review> reviews) {
        this.booktitle = booktitle;
        this.publisher = publisher;
        this.url = url;
        this.imageurl = imageurl;
        this.license = license;
        this.author = author;
        this.reviews = reviews;
    }

    public long getBookid() {
        return bookid;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}