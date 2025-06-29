package com.mahmoud.graphqlPOC.controller;

import com.mahmoud.graphqlPOC.service.AuthorService;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

// Not needed because Author field is part of the Book entity and it has a getter.
//    @SchemaMapping(typeName = "Book", field = "author")
//    public Author author(BookDTO book){
//        return authorService.getAuthorById(book.getAuthor().getId());
//    }
}
