package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.MessagesHelper;
import lv.tsi.javacourses.bookshelf.auth.boundary.CurrentUser;
import lv.tsi.javacourses.bookshelf.books.control.AuthorDAO;
import lv.tsi.javacourses.bookshelf.books.control.BookDAO;
import lv.tsi.javacourses.bookshelf.books.control.ReservationDAO;
import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;
import lv.tsi.javacourses.bookshelf.books.model.BookEntity;
import lv.tsi.javacourses.bookshelf.books.model.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BookBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(BookBean.class);
    @Inject
    private BookDAO bookDAO;
    @Inject
    private AuthorDAO authorDAO;
    @Inject
    private ReservationDAO reservationDAO;
    @Inject
    private CurrentUser currentUser;
    private Part coverPart;

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
        log.trace("Save started");
        if (coverPart != null) {
            log.debug("Uploaded file name:{}; content-type:{}; size:{}; submittedName:{}",
                    coverPart.getName(),
                    coverPart.getContentType(),
                    coverPart.getSize(),
                    coverPart.getSubmittedFileName());
            var cover = new FileInfo();
            cover.setContentType(coverPart.getContentType());
            cover.setFileName(coverPart.getSubmittedFileName());
            cover.setData(loadBytesFromPart(coverPart));
            book.setCover(cover);
        }
        if (book.getId() == null) {
            bookDAO.create(book);
        } else {
            bookDAO.update(book);
        }
        MessagesHelper.addInfoMessage(null, "Saved successfully!");
        log.trace("Save finished");
    }

    private byte[] loadBytesFromPart(Part part) {
        try (var is = part.getInputStream()) {
            return is.readAllBytes();
        } catch (IOException e) {
            log.error("Cannot read part", e);
            MessagesHelper.addErrorMessage(part.getName(), "Sorry, file not processed!");
            return null;
        }
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

    public Part getCoverPart() {
        return coverPart;
    }

    public void setCoverPart(Part coverPart) {
        this.coverPart = coverPart;
    }
}
