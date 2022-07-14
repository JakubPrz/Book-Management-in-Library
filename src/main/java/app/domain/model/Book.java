package app.domain.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Builder
@Setter(AccessLevel.NONE)
public class Book {

    @Setter
    private Long id;
    private String isbn;
    private String title;
    private String author;
    private String type;
    private String publishing;
    private Integer year;
    private BigDecimal price;

}
