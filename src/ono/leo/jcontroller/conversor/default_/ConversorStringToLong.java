package ono.leo.jcontroller.conversor.default_;

import ono.leo.jcontroller.conversor.ConversionException;
import ono.leo.jcontroller.conversor.Conversor;

/**
 * Conversor padrão para primitivo long.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/07/2011)
 */
public class ConversorStringToLong implements Conversor<String, Long> {

    @Override
    public String getAsView(Long value) throws ConversionException {
        if (value == null) return "0";
        return value.toString();
    }

    @Override
    public Long getAsBean(String value) throws ConversionException {
        long l = 0;
        if (value.trim().equals("")) return l;
        try {
            l = Long.parseLong(value);
        }
        catch (Exception ex) {
            throw new ConversionException(
                    "Valor \"" + value + "\" é invalido !");
        }
        return l;
    }
    
}
