package app.domain;

import app.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final XmlReader reader;
    private final BookRepository repository;

    public void importBooks(File file) {
        try {
            if (file.exists()) {
                List<Book> books = reader.readXml(file);
                books.forEach(repository::addBook);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
