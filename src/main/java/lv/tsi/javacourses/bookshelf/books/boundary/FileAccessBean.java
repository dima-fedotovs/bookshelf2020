package lv.tsi.javacourses.bookshelf.books.boundary;

import lv.tsi.javacourses.bookshelf.books.control.BookDAO;
import lv.tsi.javacourses.bookshelf.books.control.FileDAO;
import org.omnifaces.cdi.GraphicImageBean;

import javax.inject.Inject;
import java.io.Serializable;

@GraphicImageBean
public class FileAccessBean implements Serializable {
    @Inject
    private FileDAO fileDAO;

    public byte[] loadFile(long fileId) {
        var file = fileDAO.selectFileById(fileId);
        if (file == null) {
            return null;
        }
        return file.getData();
    }


}
