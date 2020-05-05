package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.MessagesHelper;
import lv.tsi.javacourses.bookshelf.auth.boundary.CurrentUser;
import lv.tsi.javacourses.bookshelf.books.control.AuthorDAO;
import lv.tsi.javacourses.bookshelf.books.control.BookDAO;
import lv.tsi.javacourses.bookshelf.books.control.ReservationDAO;
import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;
import lv.tsi.javacourses.bookshelf.books.model.BookEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BookBean implements Serializable {
    @Inject
    private BookDAO bookDAO;
    @Inject
    private AuthorDAO authorDAO;
    @Inject
    private ReservationDAO reservationDAO;
    @Inject
    private CurrentUser currentUser;

    private long id;
    @Valid
    private BookEntity book;
    private List<AuthorEntity> authors;

    @PostConstruct
    public void loadAuthors() {
        authors = authorDAO.selectAllAuthors();
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
        MessagesHelper.addInfoMessage(null, "Saved successfully!");
    }

    @Transactional
    public String reserve() {
        var user = currentUser.getUser();
        bookDAO.findAndLockBook(book.getId());
        var rl = reservationDAO.findActiveReservations(book, user);
        if (!rl.isEmpty()) {
            MessagesHelper.addErrorMessage(null,
                    "You have active reservation of this book");
            return null;
        }
        MessagesHelper.addInfoMessage(null, "Success!");
        reservationDAO.createReservation(book, user);
        return null;
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
