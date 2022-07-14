package app.db.springjdbc;

import app.domain.model.Book;
import app.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Repository
@RequiredArgsConstructor(access = PROTECTED)
public class BookRepositorySpringImpl implements BookRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addBook(final Book book) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("isbn", book.getIsbn())
                .addValue("title", book.getTitle())
                .addValue("author", book.getAuthor())
                .addValue("type", book.getType())
                .addValue("publishing", book.getPublishing())
                .addValue("year", book.getYear())
                .addValue("price", book.getPrice());

        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource);
        Number bookId = simpleJdbcInsert.withTableName("books")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKeyHolder(parameterSource)
                .getKey();

        if (bookId != null) {
            book.setId(bookId.longValue());
        }
    }

    @Override
    public List<Book> findAllBooks() {
        String SELECT_ALL_BOOKS = "SELECT * FROM books";
        return jdbcTemplate.query(SELECT_ALL_BOOKS, new BookRowMapper());
    }

    @Override
    public List<Book> findAllBooksAuthors() {
        String SELECT_ALL_BOOKS_AUTHORS = "SELECT * FROM books WHERE id IN (SELECT MIN(id) FROM books GROUP BY author)";
        return jdbcTemplate.query(SELECT_ALL_BOOKS_AUTHORS, new BookRowMapper());
    }

    @Override
    public List<Book> findAllBooksPublishings() {
        String SELECT_ALL_BOOKS_PUBLISHINGS = "SELECT * FROM books WHERE id IN (SELECT MIN(id) FROM books GROUP BY publishing)";
        return jdbcTemplate.query(SELECT_ALL_BOOKS_PUBLISHINGS, new BookRowMapper());
    }
}
