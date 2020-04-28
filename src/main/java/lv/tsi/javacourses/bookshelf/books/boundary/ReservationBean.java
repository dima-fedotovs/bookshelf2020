package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.control.ReservationDAO;
import lv.tsi.javacourses.bookshelf.books.model.BookEntity;
import lv.tsi.javacourses.bookshelf.books.model.ReservationEntity;
import lv.tsi.javacourses.bookshelf.books.model.ReservationStatus;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@ViewScoped
public class ReservationBean implements Serializable {
    @Inject
    private ReservationDAO reservationDAO;
    private final List<ReservationEntity> readyToGive = new ArrayList<>();
    private final List<ReservationEntity> readyToTake = new ArrayList<>();

    @PostConstruct
    public void init() {
        readyToGive.clear();
        readyToTake.clear();

        var allReservations = reservationDAO.findAllActiveReservations();
        Set<BookEntity> processedBooks = new HashSet<>();

        for (var reservation : allReservations) {
            var book = reservation.getBook();
            if (processedBooks.contains(book)) {
                continue;
            }
            processedBooks.add(book);
            if (reservation.getStatus() == ReservationStatus.ACTIVE) {
                readyToGive.add(reservation);
            } else {
                readyToTake.add(reservation);
            }
        }

    }

    public List<ReservationEntity> getReadyToGive() {
        return readyToGive;
    }

    public List<ReservationEntity> getReadyToTake() {
        return readyToTake;
    }

    public void give(ReservationEntity reservation) {
        reservation.setStatus(ReservationStatus.TAKEN);
        reservationDAO.save(reservation);
    }
}
