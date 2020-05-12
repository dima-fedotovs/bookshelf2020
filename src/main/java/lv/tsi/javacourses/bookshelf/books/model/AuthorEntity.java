package lv.tsi.javacourses.bookshelf.books.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Author")
@Table(name = "authors")
public class AuthorEntity implements Serializable, Cloneable, WithId {
    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 3, message = "Should be 3 characters at least")
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

    @Override
    public AuthorEntity clone() {
        try {
            return (AuthorEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorEntity that = (AuthorEntity) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(biography, that.biography);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, biography);
    }

    @Override
    public String toString() {
        return "AuthorEntity{" +
               "id=" + id +
               '}';
    }
}
