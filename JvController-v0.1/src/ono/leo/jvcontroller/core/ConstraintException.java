package ono.leo.jvcontroller.core;

import java.util.ArrayList;
import java.util.List;

/**
 * ConstraintException: quando ocorre erro de conversao ou validacao.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (30/07/2011 7:50)
 */
public class ConstraintException extends Exception {
    
    private String message;
    private String viewProperty;
    private List<ConstraintException> constraintExceptions 
            = new ArrayList<ConstraintException>();
    
    public ConstraintException() {
    }
    
    public ConstraintException(Throwable thrwbl) {
        super(thrwbl);
    }

    public ConstraintException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public ConstraintException(String string) {
        super(string);
    }

    public String getViewProperty() {
        return viewProperty;
    }

    public void setViewProperty(String viewProperty) {
        this.viewProperty = viewProperty;
    }

    public List<ConstraintException> getConstraintExceptions() {
        return constraintExceptions;
    }

    public void setConstraintExceptions(
            List<ConstraintException> constraintExceptions) {
        
        this.constraintExceptions = constraintExceptions;
    }
    
    public int getErrorCount() {
        return constraintExceptions.size();
    }

    @Override
    public String getMessage() {
        if (message == null) return super.getMessage();
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
