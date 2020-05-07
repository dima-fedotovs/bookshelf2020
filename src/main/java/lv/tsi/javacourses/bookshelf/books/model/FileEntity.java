package lv.tsi.javacourses.bookshelf.books.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "File")
@Table(name = "files")
public class FileEntity implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @Lob
    @Column(name = "data")
    private byte[] data;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_name")
    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] cover) {
        this.data = cover;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
