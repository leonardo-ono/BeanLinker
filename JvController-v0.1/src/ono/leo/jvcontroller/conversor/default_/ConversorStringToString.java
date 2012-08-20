package ono.leo.jvcontroller.conversor.default_;

import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 * Conversor padrão para String.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/07/2011)
 */
public class ConversorStringToString implements Conversor<String, String> {

    @Override
    public String getAsView(String value) throws ConversionException {
        if (value == null) return "";
        return value.toString();
    }

    @Override
    public String getAsBean(String value) throws ConversionException {
        return value;
    }
    
}
