package ono.leo.jcontroller.conversor.default_;

import ono.leo.jcontroller.conversor.ConversionException;
import ono.leo.jcontroller.conversor.Conversor;

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
