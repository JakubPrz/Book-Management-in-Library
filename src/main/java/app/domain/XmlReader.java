package app.domain;

import app.domain.model.Book;

import java.io.File;
import java.util.List;

public interface XmlReader {
    List<Book> readXml(File file) throws Exception;
}
