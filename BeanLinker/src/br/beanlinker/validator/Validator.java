package br.beanlinker.validator;

import br.beanlinker.updater.Updater;

/**
 * Inteface Validator.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (04/01/2013 14:30)
 */
public interface Validator {
    
    public abstract void validate(Object value, Updater updater) throws ValidationException;
    
}
