package ono.leo.jcontroller.conversor;

/**
 * Interface para conversor.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (27/07/2011)
 */
public interface Conversor<V, B> {
    
    public abstract V getAsView(B value) throws ConversionException;
    public abstract B getAsBean(V value) throws ConversionException;
    
}