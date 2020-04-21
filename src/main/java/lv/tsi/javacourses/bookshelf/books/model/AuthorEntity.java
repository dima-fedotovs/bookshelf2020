package lv.tsi.javacourses.bookshelf.books.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Author")
@Table(name = "authors")
public class AuthorEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "biography", length = 5000)
    private String biography;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
