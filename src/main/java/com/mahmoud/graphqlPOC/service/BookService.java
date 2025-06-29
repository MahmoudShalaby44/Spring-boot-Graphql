package com.mahmoud.graphqlPOC.service;

import com.mahmoud.graphqlPOC.dto.AuthorDTO;
import com.mahmoud.graphqlPOC.dto.BookDTO;
import com.mahmoud.graphqlPOC.dto.BookInput;
import com.mahmoud.graphqlPOC.entity.Author;
import com.mahmoud.graphqlPOC.entity.Book;
import com.mahmoud.graphqlPOC.exception.AuthorNotFoundException;
import com.mahmoud.graphqlPOC.exception.BookNotFoundException;
import com.mahmoud.graphqlPOC.repository.AuthorRepository;
import com.mahmoud.graphqlPOC.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookDTO> getBooks(){
        return bookRepository.findAll().stream().map(this::mapToBookDTO).toList();
    }

    public List<BookDTO> getBooksByAuthor(Long id){
        return bookRepository.findByAuthorId(id).stream().map(this::mapToBookDTO).toList();
    }

    public BookDTO insertBook(BookInput bookInput){
        Book book = mapToBook(bookInput);
        return mapToBookDTO(bookRepository.save(book));
    }

    public BookDTO updateBook(Long id, BookInput bookInput){
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
        Book mappedBook = mapToBook(bookInput);
        book.setTitle(mappedBook.getTitle());
        book.setAuthor(mappedBook.getAuthor());
        book.setReleaseYear(mappedBook.getReleaseYear());
        return mapToBookDTO(bookRepository.save(book));
    }

    public Boolean deleteBook(Long id){
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private BookDTO mapToBookDTO(Book book){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setReleaseYear(book.getReleaseYear());
        bookDTO.setAuthor(new AuthorDTO());
        bookDTO.getAuthor().setId(book.getAuthor().getId());
        bookDTO.getAuthor().setName(book.getAuthor().getName());
        bookDTO.getAuthor().setNationality(book.getAuthor().getNationality());
        bookDTO.getAuthor().setDateOfBirth(book.getAuthor().getDateOfBirth().toString());
        return bookDTO;
    }

    private Book mapToBook(BookInput bookInput){
        Author author = authorRepository.findById(bookInput.getAuthorId()).orElseThrow(() -> new AuthorNotFoundException("Author not found"));
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(bookInput.getTitle());
        book.setReleaseYear(bookInput.getReleaseYear());
        return book;
    }
}
