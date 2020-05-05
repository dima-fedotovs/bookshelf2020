package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.control.AuthorDAO;
import lv.tsi.javacourses.bookshelf.books.model.AuthorEntity;
import lv.tsi.javacourses.bookshelf.books.model.PagingInfo;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AuthorListBean implements Serializable {
    private static final int PAGE_SIZE = 25;
    private List<AuthorEntity> authors;
    @Inject
    private AuthorDAO authorDAO;
    private PagingInfo pagingInfo = new PagingInfo();


    public void init() {
        var from = PAGE_SIZE * Math.max(pagingInfo.getCurrentPage() - 1, 0);
        authors = authorDAO.selectAuthorsPage(from, PAGE_SIZE);
        var pageCount = (int)Math.ceil((double)authorDAO.selectAuthorsCount() / PAGE_SIZE);
        pagingInfo.setPageCount(pageCount);
    }

    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }
}
