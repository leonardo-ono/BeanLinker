package ono.leo.jvcontroller.conversor.default_;

import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 * Conversor padrão para boolean primitivo.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/07/2011)
 */
public class ConversorStringToBoolean implements Conversor<String, Boolean> {

    @Override
    public String getAsView(Boolean value) throws ConversionException {
        if (value == null) return "";
        return value.toString();
    }

    @Override
    public Boolean getAsBean(String value) throws ConversionException {
        if (value.trim().equals("")) return null;
        return Boolean.parseBoolean(value);
    }
    
}
