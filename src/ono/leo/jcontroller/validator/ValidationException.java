package ono.leo.jcontroller.validator;

import ono.leo.jcontroller.core.ConstraintException;

/**
 * ValidatorException: quando ocorre erro de validacao.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (30/07/2011 7:50)
 */
public class ValidationException extends ConstraintException {

    public ValidationException() {
    }
    
    public ValidationException(String string) {
        super(string, null);
    }

    public ValidationException(Throwable thrwbl) {
        super(thrwbl, null);
    }

    public ValidationException(
            String string, Throwable thrwbl) {
        
        super(string, thrwbl, null);
    }

}
