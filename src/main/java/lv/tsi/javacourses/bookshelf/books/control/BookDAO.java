package lv.tsi.javacourses.bookshelf.books.control;

import lv.tsi.javacourses.bookshelf.books.model.BookEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BookDAO {
    @PersistenceContext
    private EntityManager em;

    public BookEntity getBookById(long id) {
        return em.find(BookEntity.class, id);
    }
}
