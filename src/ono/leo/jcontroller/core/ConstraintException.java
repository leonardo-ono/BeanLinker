package ono.leo.jcontroller.core;

/**
 * ConstraintException: quando ocorre erro de conversao ou validacao.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (30/07/2011 7:50)
 */
public class ConstraintException extends Exception {
    
    private String property;
    
    public ConstraintException() {
    }
    
    public ConstraintException(Throwable thrwbl, String property) {
        super(thrwbl);
        this.property = property;
    }

    public ConstraintException(
            String string, Throwable thrwbl, String property) {
        
        super(string, thrwbl);
        this.property = property;
    }

    public ConstraintException(String string, String property) {
        super(string);
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
    
}
