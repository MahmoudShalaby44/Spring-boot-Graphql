package com.mahmoud.graphqlPOC.repository;

import com.mahmoud.graphqlPOC.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
