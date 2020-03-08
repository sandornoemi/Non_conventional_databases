package com.bookshop.admin.api;

import com.bookshop.admin.domain.Book;
import com.bookshop.admin.exception.RecordNotFoundException;
import com.bookshop.admin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBook(){
        List<Book> listOfBooks = bookService.getAllBooks();

        return new ResponseEntity<>(listOfBooks, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Book book = bookService.getBookById(id);

        return new ResponseEntity<>(book, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> createOrUpdateBook(Book book) {
        Book bookEntity = bookService.createOrUpdateBook(book);

        return new ResponseEntity<>(bookEntity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping
    public HttpStatus deleteBookById(@PathVariable("id") Long id) throws RecordNotFoundException {
        bookService.deleteBookById(id);

        return HttpStatus.FORBIDDEN;
    }
}
