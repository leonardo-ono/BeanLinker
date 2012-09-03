package compra.pedido.conversor;

import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 *
 * @author leonardo
 */
public class BooleanConversor implements Conversor {

    public Object getAsView(Object value) throws ConversionException {
        if (value == null) {
            return "NAO";
        }
        return ((Boolean) value ? "SIM" : "NAO");
    }

    public Object getAsModel(Object value) throws ConversionException {
        if (value==null || value.toString().length()==0) {
            return false;
        }
        return value.toString().trim().toUpperCase().equals("SIM");
    }
    
}
