package com.mahmoud.graphqlPOC.service;

import com.mahmoud.graphqlPOC.dto.AuthorDTO;
import com.mahmoud.graphqlPOC.entity.Author;
import com.mahmoud.graphqlPOC.exception.AuthorNotFoundException;
import com.mahmoud.graphqlPOC.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author not found"));
        return mapToAuthorDTO(author);
    }

    private AuthorDTO mapToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setDateOfBirth(author.getDateOfBirth().toString());
        authorDTO.setNationality(author.getNationality());
        return authorDTO;
    }

    private Author mapToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setDateOfBirth(LocalDate.parse(authorDTO.getDateOfBirth()));
        author.setNationality(authorDTO.getNationality());
        return author;
    }
}
