package app.xml.jaxb.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import app.domain.model.Book;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class BookXml {

    @XmlAttribute(name = "id")
    private Long id;

    @XmlAttribute(name = "isbn")
    private String isbn;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "author")
    private String author;

    @XmlElement(name = "type")
    private String type;

    @XmlElement(name = "publishing")
    private String publishing;

    @XmlElement(name = "year")
    private Integer year;

    @XmlElement(name = "price")
    private BigDecimal price;

    Book toDomain() {
        return Book.builder()
                .isbn(isbn)
                .title(title)
                .author(author)
                .type(type)
                .publishing(publishing)
                .year(year)
                .price(price)
                .build();
    }
}
