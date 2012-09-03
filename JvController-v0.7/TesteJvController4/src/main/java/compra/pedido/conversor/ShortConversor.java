package compra.pedido.conversor;

import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 *
 * @author leonardo
 */
public class ShortConversor implements Conversor {

    public Object getAsView(Object value) throws ConversionException {
        if (value == null) {
            return "0";
        }
        return value.toString();
    }

    public Object getAsModel(Object value) throws ConversionException {
        if (value==null || value.toString().length()==0) {
            return (Short) (short) 0;
        }
        return Short.valueOf(value.toString());
    }
    
}
