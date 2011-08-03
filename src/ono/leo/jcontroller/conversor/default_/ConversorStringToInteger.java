package ono.leo.jcontroller.conversor.default_;

import ono.leo.jcontroller.conversor.ConversionException;
import ono.leo.jcontroller.conversor.Conversor;

/**
 * Conversor padrão para objeto Integer.
 * 
 * Diferenca eh que no objeto Integer, "" representa null
 * enquanto no primitivo representa 0.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (28/07/2011)
 */
public class ConversorStringToInteger implements Conversor<String, Integer> {

    @Override
    public String getAsView(Integer value) throws ConversionException {
        if (value == null) return null;
        return value.toString();
    }

    @Override
    public Integer getAsBean(String value) throws ConversionException {
        Integer i = 0;
        if (value.trim().equals("")) return null;
        try {
            i = Integer.valueOf(value);
        }
        catch (Exception ex) {
            throw new ConversionException(
                    "Valor \"" + value + "\" é invalido !");
        }
        return i;
    }
    
}
