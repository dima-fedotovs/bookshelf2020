package lv.tsi.javacourses.bookshelf.books.boundary.converters;

import lv.tsi.javacourses.bookshelf.books.model.WithId;

import javax.enterprise.inject.Model;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Named
@ViewScoped
@FacesConverter("mapping-object-converter")
public class MappingObjectConverter implements Converter<WithId>, Serializable {
    private final Map<Long, WithId> storage = new ConcurrentHashMap<>();

    @Override
    public WithId getAsObject(FacesContext context, UIComponent component, String key) {
        if (key == null) {
            return null;
        }
        return storage.get(Long.valueOf(key));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, WithId value) {
        if (value == null) {
            return null;
        }
        var key = value.getId();
        storage.put(key, value);
        return String.valueOf(key);
    }
}
