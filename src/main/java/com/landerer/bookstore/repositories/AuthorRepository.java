package com.landerer.bookstore.repositories;

import com.landerer.bookstore.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long>, JpaRepository<Author, Long> {
}
