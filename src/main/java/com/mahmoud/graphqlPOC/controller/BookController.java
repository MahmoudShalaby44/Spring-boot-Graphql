package com.mahmoud.graphqlPOC.controller;

import com.mahmoud.graphqlPOC.dto.BookDTO;
import com.mahmoud.graphqlPOC.dto.BookInput;
import com.mahmoud.graphqlPOC.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<BookDTO> books(){
        return bookService.getBooks();
    }

    //argument name must be the same as the one defined in the schema
    @QueryMapping
    public List<BookDTO> book(@Argument Long authorId){
        return bookService.getBooksByAuthor(authorId);
    }

    @MutationMapping
    public BookDTO insertBook(@Argument BookInput book){
        return bookService.insertBook(book);
    }

    @MutationMapping
    BookDTO updateBook(@Argument Long bookId, @Argument BookInput book){
        return bookService.updateBook(bookId, book);
    }

    @MutationMapping
    Boolean deleteBook(@Argument Long bookId){
        return bookService.deleteBook(bookId);
    }
}
