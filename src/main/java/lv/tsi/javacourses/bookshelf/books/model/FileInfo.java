package lv.tsi.javacourses.bookshelf.books.model;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class FileInfo {
    @Lob
    private byte[] data;

    private String contentType;

    private String fileName;

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
