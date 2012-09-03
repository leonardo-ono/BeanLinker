package teste.conversor;

import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 *
 * @author leonardo
 */
public class IntegerConversor implements Conversor {

    public Object getAsView(Object value) throws ConversionException {
        if (value == null) {
            return "0";
        }
        return value.toString();
    }

    public Object getAsModel(Object value) throws ConversionException {
        if (value==null || value.toString().length()==0) {
            return 0;
        }
        return Integer.valueOf(value.toString().trim());
    }
    
}
