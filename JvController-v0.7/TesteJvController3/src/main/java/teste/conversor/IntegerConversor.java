package teste.conversor;

import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 *
 * @author leonardo
 */
public class IntegerConversor implements Conversor {

    public Object getAsView(Object value) throws ConversionException {
        return value.toString();
    }

    public Object getAsModel(Object value) throws ConversionException {
        return Integer.valueOf(value.toString());
    }
    
}
