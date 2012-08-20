package teste.conversor;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 *
 * @author leonardo
 */
public class DataConversor implements Conversor {

    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    
    public Object getAsView(Object value) throws ConversionException {
        return df.format((Date) value);
    }

    public Object getAsModel(Object value) throws ConversionException {
        Object data = null;
        try {
            data = df.parse(value.toString());
        } catch (ParseException ex) {
            throw new ConversionException("Invalid data !", ex);
        }
        return data;
    }
    
}
