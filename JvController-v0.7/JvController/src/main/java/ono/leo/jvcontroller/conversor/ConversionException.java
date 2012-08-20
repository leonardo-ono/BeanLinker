package ono.leo.jvcontroller.conversor;

/**
 * ConversionException: quando ocorre erro de conversao.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (16/08/2012 15:27)
 */
public class ConversionException extends Exception {

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
