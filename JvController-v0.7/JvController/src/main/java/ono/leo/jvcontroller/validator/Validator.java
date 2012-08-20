package ono.leo.jvcontroller.validator;

/**
 * Interface para validador.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 15:26)
 */

public interface Validator {
    
    public abstract void validate(Object valor) throws ValidationException;
    
}
