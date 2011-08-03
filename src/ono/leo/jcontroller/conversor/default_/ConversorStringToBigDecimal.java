package ono.leo.jcontroller.conversor.default_;

import java.math.BigDecimal;
import ono.leo.jcontroller.conversor.ConversionException;
import ono.leo.jcontroller.conversor.Conversor;

/**
 *
 * @author leo
 */
public class ConversorStringToBigDecimal implements Conversor<String, BigDecimal> {

    @Override
    public String getAsView(BigDecimal value) throws ConversionException {
        if (value == null) return "";
        BigDecimal valor = (BigDecimal) value;
        String valorEmString 
                = valor.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
        
        valorEmString = valorEmString.replace(".", ",");
        return valorEmString;
    }

    @Override
    public BigDecimal getAsBean(String value) throws ConversionException {
        value = value.replace(".", "");
        value = value.replace(",", ".");
        if (value == null || value.trim().equals("")) return null;
        BigDecimal v = null;
        try {
            v = new BigDecimal(value);
        }
        catch (Exception ex) {
            throw new ConversionException(
                    "Valor \"" + value + "\" é invalido !");
        }
        return v;
    }
    
}
