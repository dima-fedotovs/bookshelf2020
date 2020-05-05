package lv.tsi.javacourses.bookshelf.books.control;

import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AuthorDAO {
    @PersistenceContext
    private EntityManager em;

    public List<AuthorEntity> selectAllAuthors() {
        return em.createQuery("select b from Author b", AuthorEntity.class)
                .getResultList();
    }

    public List<AuthorEntity> selectAuthorsPage(int from, int size) {
        return em.createQuery("select b from Author b", AuthorEntity.class)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }

    public AuthorEntity selectAuthorById(long id) {
        return em.find(AuthorEntity.class, id);
    }

    public long selectAuthorsCount() {
        return em.createQuery("select count(a) from Author a", Long.class)
                .getSingleResult();
    }

    public AuthorEntity update(AuthorEntity author) {
        return em.merge(author);
    }

    public void create(AuthorEntity author) {
        em.persist(author);
    }

    public void delete(AuthorEntity author) {
        var tmp = em.merge(author);
        em.remove(tmp);
    }
}
