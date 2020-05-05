package lv.tsi.javacourses.bookshelf.books.model;

public class PagingInfo {
    private int pageCount;
    private int currentPage = 1;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
