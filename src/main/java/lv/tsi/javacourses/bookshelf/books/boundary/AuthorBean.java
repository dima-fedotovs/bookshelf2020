package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.control.AuthorDAO;
import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AuthorBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(AuthorBean.class);
    @Inject
    private AuthorDAO authorDAO;

    private AuthorEntity author;
    private long id;

    public void change() {
        log.info("CHANGED");
    }

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

    public String delete() {
        if (author.getId() == null) {
            return null;
        }

        try {
            authorDAO.delete(author);
        } catch (Exception e) {
            log.error("Cannot delete author " + author.getId(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot delete", "Cannot delete")
            );
            return null;
        }

        return "success";
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
