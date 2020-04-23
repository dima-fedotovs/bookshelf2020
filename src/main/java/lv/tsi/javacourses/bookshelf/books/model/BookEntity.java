package lv.tsi.javacourses.bookshelf.books.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Book")
@Table(name = "books")
public class BookEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "title", length = 500, nullable = false)
    private String title;
    @Column(name = "isbn", length = 50, nullable = false)
    private String isbn;
    @Column(name = "year", nullable = false)
    private int year;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorEntity author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }
}
