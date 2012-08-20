package jbean2.conversor;

/**
 *
 * @author leo
 */
public interface Conversor {

    public Object getAsView(Object value) throws Exception;
    public Object getAsModel(Object value) throws Exception;
    
}

