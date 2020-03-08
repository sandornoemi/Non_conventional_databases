package com.bookshop.admin.repository;

import com.bookshop.admin.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {}
