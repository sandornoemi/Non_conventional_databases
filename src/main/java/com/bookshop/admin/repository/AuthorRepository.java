package com.bookshop.admin.repository;

import com.bookshop.admin.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {}
