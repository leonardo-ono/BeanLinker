package br.beanlinker.updater;

import br.beanlinker.converter.ConversionException;
import br.beanlinker.validator.ValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class UpdateException.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (04/01/2012 15:54)
 */
public class UpdateException extends Exception {
    
    List<ConversionException> conversionExceptions = new ArrayList<ConversionException>();
    List<ValidationException> validationExceptions = new ArrayList<ValidationException>();

    public UpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateException(String message) {
        super(message);
    }

    public UpdateException() {
    }

    public List<ConversionException> getConversionExceptions() {
        return conversionExceptions;
    }

    public List<ValidationException> getValidationExceptions() {
        return validationExceptions;
    }

}
