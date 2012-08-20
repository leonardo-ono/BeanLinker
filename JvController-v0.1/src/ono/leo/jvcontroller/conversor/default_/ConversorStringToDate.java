package ono.leo.jvcontroller.conversor.default_;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 * Conversor padrão para java.util.Date.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/07/2011)
 */
public class ConversorStringToDate implements Conversor<String, Date> {
    
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    @Override
    public String getAsView(Date value) throws ConversionException {
        if (value == null) return "";
        Date valor = (Date) value;
        String valorEmString = df.format(valor) ;
        return valorEmString;
    }

    @Override
    public Date getAsBean(String value) throws ConversionException {
        if (value == null || value.trim().equals("")) return null;
        Date d = null;
        try {
            d = df.parse(value);
        }
        catch (Exception ex) {
            throw new ConversionException("Valor \"" + value 
                    + "\" é invalido ! Precisa estar no formato DD/MM/AAAA.");
        }
        return d;
    }
    
}
