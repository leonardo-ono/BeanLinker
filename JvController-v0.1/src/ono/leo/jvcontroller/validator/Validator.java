package ono.leo.jvcontroller.validator;

/**
 * Interface para validador.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/07/2011)
 */

public interface Validator<T> {
    
    public abstract void validate(T valor) throws ValidationException;
    
}
