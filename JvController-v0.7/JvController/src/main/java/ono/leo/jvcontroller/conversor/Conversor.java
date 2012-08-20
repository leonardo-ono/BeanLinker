package ono.leo.jvcontroller.conversor;

/**
 * Conversor interface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 10:53)
 */
public interface Conversor {

    public Object getAsView(Object value) throws ConversionException;
    public Object getAsModel(Object value) throws ConversionException;
    
}

