package lv.tsi.javacourses.bookshelf.books.boundary;


import lv.tsi.javacourses.bookshelf.books.model.BookEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BooksBean implements Serializable {
    @PersistenceContext
    private EntityManager em;
    private List<BookEntity> books;

    @PostConstruct
    public void init() {
        books = em.createQuery("select b from Book b", BookEntity.class)
                .getResultList();
    }

    public List<BookEntity> getBooks() {
        return books;
    }
}
