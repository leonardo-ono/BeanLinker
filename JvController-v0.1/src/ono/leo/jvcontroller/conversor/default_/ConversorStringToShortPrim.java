package ono.leo.jvcontroller.conversor.default_;

import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 * Conversor padrão para short primitivo.
 * 
 * Diferenca eh que no objeto Short, "" representa null
 * enquanto no primitivo representa 0.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (28/07/2011)
 */
public class ConversorStringToShortPrim implements Conversor<String, Short> {

    @Override
    public String getAsView(Short value) throws ConversionException {
        if (value == null) return "0";
        return value.toString();
    }

    @Override
    public Short getAsBean(String value) throws ConversionException {
        short s = 0;
        if (value.trim().equals("")) return s;
        try {
            s = Short.parseShort(value);
        }
        catch (Exception ex) {
            throw new ConversionException(
                    "Valor \"" + value + "\" é invalido !");
        }
        return s;
    }
    
}
