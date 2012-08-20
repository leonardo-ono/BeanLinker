package jbean2.conversor;

/**
 *
 * @author leo
 */
@jbean2.annotation.Conversor("IntegerConversor")
public class IntegerConversor implements Conversor {

    @Override
    public Object getAsView(Object value) throws Exception  {
        return String.valueOf(value);
    }
    
    @Override
    public Object getAsModel(Object value) throws Exception {
        return Integer.valueOf(value.toString());
    }
    
}
