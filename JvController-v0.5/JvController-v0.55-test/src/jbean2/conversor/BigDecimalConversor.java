package jbean2.conversor;

import java.math.BigDecimal;

/**
 *
 * @author leo
 */
@jbean2.annotation.Conversor("BigDecimalConversor")
public class BigDecimalConversor implements Conversor {

    @Override
    public Object getAsView(Object value) throws Exception  {
        return ((BigDecimal) value).toPlainString();
    }
    
    @Override
    public Object getAsModel(Object value) throws Exception {
        return new BigDecimal(value.toString());
    }
    
}
