package teste2;

import br.beanlinker.converter.ConversionException;
import br.beanlinker.converter.Converter;
import br.beanlinker.updater.Updater;

/**
 *
 * @author leonardo
 */
public class IntegerConversor implements Converter {

    @Override
    public Object get(Object value, Updater updater) throws ConversionException {
        if (value instanceof Integer) {
            return getAsString(value, updater);
        }
        else if (value instanceof String) {
            return getAsInteger(value, updater);
        }
        throw new RuntimeException("StringIntegerConversor: value is invalid !");
    }
    
    public Object getAsString(Object value, Updater updater) throws ConversionException {
        if (value == null) {
            return "0";
        }
        return value.toString();
    }

    public Object getAsInteger(Object value, Updater updater) throws ConversionException {
        try {
            if (value==null || value.toString().length()==0) {
                return 0;
            }
            return Integer.valueOf(value.toString().trim());
        }
        catch (Exception e) {
            throw new ConversionException("Valor \"" + value + "\" inválido !", updater);
        }
    }
    
}
