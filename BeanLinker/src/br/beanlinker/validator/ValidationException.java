package br.beanlinker.validator;

import br.beanlinker.updater.Updater;

/**
 * Class ValidationException.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (04/01/2013 14:30)
 */

public class ValidationException extends Exception {
    
    private Updater updater;
    
    public ValidationException(String message, Updater updater) {
        super(message);
        this.updater = updater;
    }

    public Updater getUpdater() {
        return updater;
    }

}
