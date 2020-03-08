package com.bookshop.admin.service;

import com.bookshop.admin.domain.Author;
import com.bookshop.admin.domain.Publisher;
import com.bookshop.admin.exception.RecordNotFoundException;
import com.bookshop.admin.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        List<Author> authorList = authorRepository.findAll();

        if (authorList.size() > 0) {
            return authorList;
        } else {
            return new ArrayList<>();
        }
    }

    public Author getAuthorById(Long id) throws RecordNotFoundException {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isPresent()) {
            return author.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }

    public Author createOrUpdateAuthor(Author author) {
        Optional<Author> authorEntity = authorRepository.findById(author.getId());

        if (authorEntity.isPresent()) {
            Author updateAuthor = authorEntity.get();
            updateAuthor.setFirstName(author.getFirstName());
            updateAuthor.setLastName(author.getLastName());

            authorRepository.save(updateAuthor);

            return updateAuthor;
        } else {
            authorRepository.save(author);

            return author;
        }
    }

    public void deleteAuthorById(Long id) throws RecordNotFoundException {
        Optional<Author> author = authorRepository.findById(id);

        if (author.isPresent()) {
            authorRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}
