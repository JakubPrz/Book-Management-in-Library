package app.db.springjdbc;

import app.domain.model.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        return Book.builder()
                .id(rs.getLong(1))
                .isbn(rs.getString(2))
                .title(rs.getString(3))
                .author(rs.getString(4))
                .type(rs.getString(5))
                .publishing(rs.getString(6))
                .year(rs.getInt(7))
                .price(rs.getBigDecimal(8))
                .build();
    }
}