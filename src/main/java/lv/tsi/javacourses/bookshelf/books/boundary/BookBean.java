package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.control.AuthorDAO;
import lv.tsi.javacourses.bookshelf.books.control.BookDAO;
import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;
import lv.tsi.javacourses.bookshelf.books.model.BookEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BookBean implements Serializable {
    @Inject
    private BookDAO bookDAO;
    @Inject
    private AuthorDAO authorDAO;
    private long id;
    private BookEntity book;
    private List<AuthorEntity> authors;

    @PostConstruct
    public void loadAuthors() {
        authors = authorDAO.getAllAuthors();
    }

    public void loadBook() {
        book = bookDAO.getBookById(id);
    }

    public void save() {
        if (book.getId() == null) {
            bookDAO.create(book);
        } else {
            bookDAO.update(book);
        }
    }

    public BookEntity getBook() {
        return book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

}
