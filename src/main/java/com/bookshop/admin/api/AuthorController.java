package com.bookshop.admin.api;

import com.bookshop.admin.domain.Author;
import com.bookshop.admin.exception.RecordNotFoundException;
import com.bookshop.admin.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthor() {
        List<Author> authorList = authorService.getAllAuthors();

        return new ResponseEntity<>(authorList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable("id") Long id) throws RecordNotFoundException {
        Author author = authorService.getAuthorById(id);

        return new ResponseEntity<>(author, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> createOrUpdateAuthor(Author author) {
        Author authorEntity = authorService.createOrUpdateAuthor(author);

        return new ResponseEntity<>(authorEntity, new HttpHeaders(), HttpStatus.OK);
    }

    @DeleteMapping
    public HttpStatus deleteAuthor(@PathVariable("id") Long id) throws RecordNotFoundException {
        authorService.deleteAuthorById(id);

        return HttpStatus.FORBIDDEN;
    }
}
