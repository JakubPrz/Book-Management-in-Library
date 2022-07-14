package app.shell;

import app.domain.ApplicationService;
import app.domain.BookService;
import app.domain.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.shell.table.TableModel;

import java.io.File;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;
    private final ApplicationService applicationService;

    @ShellMethod(value = "shows books from database", key = "show books")
    public String showBooks() {
        List<Book> books = bookService.getAll();

        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "Id");
        headers.put("isbn", "Isbn");
        headers.put("title", "Title");
        headers.put("author", "Author");
        headers.put("type", "Type");
        headers.put("publishing", "Publishing");
        headers.put("year", "Year");
        headers.put("price", "Price");

        TableModel model = new BeanListTableModel<>(books, headers);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.oldschool);
        tableBuilder.addHeaderBorder(BorderStyle.oldschool);
        return tableBuilder.build().render(200);
    }

    @ShellMethod(value = "shows authors from database", key = "show authors")
    public String showAuthors() {
        List<Book> books = bookService.getAllBooksAuthors();
        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();

        headers.put("author", "Author");

        TableModel model = new BeanListTableModel<>(books, headers);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.oldschool);
        tableBuilder.addHeaderBorder(BorderStyle.oldschool);
        return tableBuilder.build().render(200);
    }


    @ShellMethod(value = "shows publishings from database", key = "show publishings")
    public String showPublishings() {
        List<Book> books = bookService.getAllBooksPublishings();
        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();

        headers.put("publishing", "Publishing");

        TableModel model = new BeanListTableModel<>(books, headers);
        TableBuilder tableBuilder = new TableBuilder(model);
        tableBuilder.addInnerBorder(BorderStyle.oldschool);
        tableBuilder.addHeaderBorder(BorderStyle.oldschool);
        return tableBuilder.build().render(200);
    }

    @ShellMethod(value = "imports books from xml", key = "import")
    public void importBooks(String fileName) {
        File file = Paths.get(fileName).toFile();
        applicationService.importBooks(file);
    }
}
