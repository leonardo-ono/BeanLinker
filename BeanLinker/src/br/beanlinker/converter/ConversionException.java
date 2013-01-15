package br.beanlinker.converter;

import br.beanlinker.link.Link;
import br.beanlinker.updater.Updater;

/**
 * Class ConversionException.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (04/01/2013 14:30)
 */
public class ConversionException extends Exception {

    private Updater updater;
    
    public ConversionException() {
    }

    public ConversionException(String message, Updater updater) {
        super(message);
        this.updater = updater;
    }

    public Updater getUpdater() {
        return updater;
    }

}
