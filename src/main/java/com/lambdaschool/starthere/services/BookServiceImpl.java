package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.models.Review;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repo;



    @Override
    public List<Book> findAll(Pageable pageable) {
        List<Book> bookList = new ArrayList<>();
        repo.findAll(pageable).iterator().forEachRemaining(bookList::add);
        return bookList;
    }

    @Transactional
    @Override
    public Book updateBook(Book book, long id) {
        Book currentBook = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        if(book.getBooktitle() != null){
            currentBook.setBooktitle(book.getBooktitle());
        }//TODO: Add the rest of the fields to be updated
        repo.save(currentBook);
        return currentBook;
    }



    @Transactional
    @Override
    public void delete(long id) {
        if (repo.findById(id).isPresent()){
            repo.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }

    }

    @Override
    public void save(Book book) {
        repo.save(book);
    }

    @Override
    public Book findBookById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public boolean addReview(Review review, long id) {
        Book currentBook = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        return currentBook.getReviews().add(review);
    }

}