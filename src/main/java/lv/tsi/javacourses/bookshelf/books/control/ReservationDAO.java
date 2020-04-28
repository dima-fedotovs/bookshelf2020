package lv.tsi.javacourses.bookshelf.books.control;

import lv.tsi.javacourses.bookshelf.auth.model.UserEntity;
import lv.tsi.javacourses.bookshelf.books.model.BookEntity;
import lv.tsi.javacourses.bookshelf.books.model.ReservationEntity;
import lv.tsi.javacourses.bookshelf.books.model.ReservationStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ReservationDAO {
    @PersistenceContext
    private EntityManager em;

    public ReservationEntity createReservation(BookEntity book, UserEntity user) {
        var result = new ReservationEntity();
        result.setBook(book);
        result.setUser(user);
        result.setStatus(ReservationStatus.ACTIVE);
        em.persist(result);
        return result;
    }

    public List<ReservationEntity> findActiveReservations(BookEntity book, UserEntity user) {
        return em.createQuery("select r from Reservation r " +
                              "where r.book = :book and r.user = :user and r.status <> 'CLOSED'" +
                              "order by r.create", ReservationEntity.class)
                .setParameter("user", user)
                .setParameter("book", book)
                .getResultList();
    }

    public List<ReservationEntity> findAllActiveReservations() {
        return em.createQuery("select r from Reservation r " +
                              "where r.status <> 'CLOSED'" +
                              "order by r.create", ReservationEntity.class)
                .getResultList();
    }

    public void save(ReservationEntity reservation) {
        em.merge(reservation);
    }
}
