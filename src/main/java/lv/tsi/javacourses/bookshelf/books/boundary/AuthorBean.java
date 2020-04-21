package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.control.AuthorDAO;
import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AuthorBean implements Serializable {
    @Inject
    private AuthorDAO authorDAO;

    private AuthorEntity author;
    private long id;

    public void find() {
        if (id == 0) {
            author = new AuthorEntity();
        } else {
            author = authorDAO.getAuthorById(id);
        }
    }

    public void save() {
        if (author.getId() == null) {
            authorDAO.create(author);
        } else {
            author = authorDAO.update(author);
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AuthorEntity getAuthor() {
        return author;
    }
}
