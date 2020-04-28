package lv.tsi.javacourses.bookshelf.books.control;

import lv.tsi.javacourses.bookshelf.auth.boundary.CurrentUser;
import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;
import lv.tsi.javacourses.bookshelf.books.model.BookEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookDAO {
    @PersistenceContext
    private EntityManager em;

    public BookEntity getBookById(long id) {
        return em.find(BookEntity.class, id);
    }

    public BookEntity findAndLockBook(long bookId) {
        return em.find(BookEntity.class, bookId, LockModeType.PESSIMISTIC_WRITE);
    }

    public List<BookEntity> getBooksByAuthor(AuthorEntity author) {
        return em.createQuery("select b from Book b where b.author = :author", BookEntity.class)
                .setParameter("author", author)
                .getResultList();
    }

    public List<BookEntity> getBooksByAuthorId(long authorId) {
        return em.createQuery("select b from Book b where b.author.id = :authorId", BookEntity.class)
                .setParameter("authorId", authorId)
                .getResultList();
    }

    public void create(BookEntity book) {
        em.persist(book);
    }

    public void update(BookEntity book) {
        em.merge(book);
    }
}
