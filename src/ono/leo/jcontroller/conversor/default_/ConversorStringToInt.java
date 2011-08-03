package ono.leo.jcontroller.conversor.default_;

import ono.leo.jcontroller.conversor.ConversionException;
import ono.leo.jcontroller.conversor.Conversor;

/**
 * Conversor padrão para primitivo int.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/07/2011)
 */
public class ConversorStringToInt implements Conversor<String, Integer> {

    @Override
    public String getAsView(Integer value) throws ConversionException {
        if (value == null) return "0";
        return value.toString();
    }

    @Override
    public Integer getAsBean(String value) throws ConversionException {
        int i = 0;
        if (value.trim().equals("")) return i;
        try {
            i = Integer.parseInt(value);
        }
        catch (Exception ex) {
            throw new ConversionException(
                    "Valor \"" + value + "\" é invalido !");
        }
        return i;
    }
    
}
