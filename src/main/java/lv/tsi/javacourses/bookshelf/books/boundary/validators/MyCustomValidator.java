package lv.tsi.javacourses.bookshelf.books.boundary.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("mycustom")
public class MyCustomValidator implements Validator<String> {
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        if (value == null || value.isBlank() || value.length() % 2 == 0) {
            var msg = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Wrong value",
                    "Size should be even");
            throw new ValidatorException(msg);
        }
    }
}
