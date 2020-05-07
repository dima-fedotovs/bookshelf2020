package lv.tsi.javacourses.bookshelf.books.control;

import lv.tsi.javacourses.bookshelf.books.model.FileEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FileDAO {
    @PersistenceContext
    private EntityManager em;

    public FileEntity selectFileById(long id) {
        return em.find(FileEntity.class, id);
    }

    public void save(FileEntity fileEntity) {
        if (fileEntity.getId() == null) {
            em.persist(fileEntity);
        } else {
            em.merge(fileEntity);
        }
    }
}
