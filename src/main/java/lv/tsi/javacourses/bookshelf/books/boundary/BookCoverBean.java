package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.control.BookDAO;
import org.omnifaces.cdi.GraphicImageBean;

import javax.inject.Inject;
import java.io.Serializable;

@GraphicImageBean
public class BookCoverBean implements Serializable {
    @Inject
    private BookDAO bookDAO;

    public byte[] loadCover(long bookId) {
        var book = bookDAO.getBookById(bookId);
        if (book == null) {
            return null;
        }
        var cover = book.getCover();
        if (cover == null) {
            return null;
        }
        return cover.getData();
    }


}
