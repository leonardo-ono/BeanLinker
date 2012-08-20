package ono.leo.jvcontroller.conversor;

import ono.leo.jvcontroller.core.ConstraintException;

/**
 * ConversionException: quando ocorre erro de conversao.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (30/07/2011 7:50)
 */
public class ConversionException extends ConstraintException {

    public ConversionException() {
    }
    
    public ConversionException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ConversionException(
            String string, Throwable thrwbl) {
        
        super(string, thrwbl);
    }

    public ConversionException(String string) {
        super(string);
    }

}
