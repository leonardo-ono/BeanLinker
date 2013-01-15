package br.beanlinker.converter;

import br.beanlinker.updater.Updater;

/**
 * Interface Converter.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (04/01/2013 14:30)
 */

public interface Converter {

    public Object get(Object value, Updater updater) throws ConversionException;
    
}

