package com.mahmoud.graphqlPOC.repository;

import com.mahmoud.graphqlPOC.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);
}
