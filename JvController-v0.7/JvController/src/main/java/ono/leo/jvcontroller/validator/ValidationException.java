package ono.leo.jvcontroller.validator;

/**
 * ValidatorException: quando ocorre erro de validacao.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 15:25)
 */
public class ValidationException extends Exception {
    
    public ValidationException() {
    }
    
    public ValidationException(String string) {
        super(string);
    }

    public ValidationException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ValidationException(
            String string, Throwable thrwbl) {
        
        super(string, thrwbl);
    }

}
