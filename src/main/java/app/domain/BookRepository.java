package app.domain;

import app.domain.model.Book;

import java.util.List;

public interface BookRepository {
    void addBook(Book book);

    List<Book> findAllBooks();
    List<Book> findAllBooksAuthors();
    List<Book> findAllBooksPublishings();

}
