package app.domain;

import app.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAllBooks();
    }

    public List<Book> getAllBooksAuthors() {
        return bookRepository.findAllBooksAuthors();
    }

    public List<Book> getAllBooksPublishings() {
        return bookRepository.findAllBooksPublishings();
    }

}
