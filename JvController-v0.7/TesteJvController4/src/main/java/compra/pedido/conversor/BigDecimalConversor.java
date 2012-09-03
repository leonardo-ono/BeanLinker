package compra.pedido.conversor;

import java.math.BigDecimal;
import ono.leo.jvcontroller.conversor.ConversionException;
import ono.leo.jvcontroller.conversor.Conversor;

/**
 *
 * @author leonardo
 */
public class BigDecimalConversor implements Conversor {

    public Object getAsView(Object value) throws ConversionException {
        if (value == null) {
            return "";
        }
        return ((BigDecimal) value).toPlainString().replace(".", ",");
    }

    public Object getAsModel(Object value) throws ConversionException {
        if (value==null || value.toString().length()==0) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(value.toString().replace(".", "").replace(",", "."));
    }
    
}
