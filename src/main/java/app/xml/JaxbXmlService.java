package app.xml;

import app.domain.model.Book;
import app.domain.XmlReader;
import app.xml.jaxb.model.BooksXml;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

@Component
public class JaxbXmlService implements XmlReader {

    private final Unmarshaller unmarshaller;

    public JaxbXmlService() throws Exception {
        JAXBContext context = JAXBContext.newInstance(BooksXml.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public List<Book> readXml(final File file) throws Exception {
        BooksXml books = (BooksXml) unmarshaller.unmarshal(file);

        return books.toDomain();
    }
}
