package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.control.AuthorDAO;
import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AuthorListBean implements Serializable {
    private List<AuthorEntity> authors;
    @Inject
    private AuthorDAO authorDAO;

    @PostConstruct
    public void init() {
        authors = authorDAO.getAllAuthors();
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }
}
