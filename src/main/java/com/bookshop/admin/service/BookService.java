package com.bookshop.admin.service;

import com.bookshop.admin.domain.Book;
import com.bookshop.admin.exception.RecordNotFoundException;
import com.bookshop.admin.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        List<Book> listOfBooks = bookRepository.findAll();

        if (listOfBooks.size() > 0) {
            return listOfBooks;
        } else {
            return new ArrayList<>();
        }
    }

    public Book getBookById(Long id) throws RecordNotFoundException {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return book.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public Book createOrUpdateBook(Book book) {
        Optional<Book> bookEntity = bookRepository.findById(book.getId());

        if (bookEntity.isPresent()) {
            Book updateBook = bookEntity.get();

            updateBook.setTitle(book.getTitle());
            updateBook.setYearOfPublication(book.getYearOfPublication());
            updateBook.setIsbn(book.getIsbn());
            updateBook.setCategory(book.getCategory());
            updateBook.setNumberOfPages(book.getNumberOfPages());
            updateBook.setShortDescription(book.getShortDescription());
            updateBook.setPrice(book.getPrice());
            updateBook.setAuthor(book.getAuthor());
            updateBook.setPublisher(book.getPublisher());

            bookRepository.save(updateBook);

            return updateBook;
        } else {
            bookRepository.save(book);

            return book;
        }
    }

    public void deleteBookById(Long id) throws RecordNotFoundException {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
