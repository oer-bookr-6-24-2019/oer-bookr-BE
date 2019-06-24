package com.lambdaschool.starthere.services;


import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> findAll(Pageable pageable);

    Book updateBook(Book book, long id);


    void delete(long id);

    void save(Book book);

    Book findBookById(long id);

    boolean addReview(Review review, long id);
}