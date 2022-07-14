package app.xml.jaxb.model;

import app.domain.model.Book;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement(name = "books")
@XmlAccessorType(XmlAccessType.FIELD)
public class BooksXml {

    @XmlElement(name = "book")
    private List<BookXml> books;

    public List<Book> toDomain() {
        List<Book> collectedBooks;
        collectedBooks = books.stream()
                .map(BookXml::toDomain)
                .collect(Collectors.toUnmodifiableList());

        return collectedBooks;
    }
}
