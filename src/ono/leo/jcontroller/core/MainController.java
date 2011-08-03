package ono.leo.jcontroller.core;

//import ono.leo.mvc.conversor.Conversor;
//import ono.leo.mvc.validator.Validator;

/**
 * Interface para Main Controller.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00 (01/08/2011 09:14)
 */
public interface MainController {

    public abstract void addView(String id, Object view);
    public abstract void addBean(String id, Object bean);
    public abstract void addConversor(String id, Object conversor);
    public abstract void addValidator(String id, Object validator);

    public abstract void bindProperty(
            String viewProperty, String beanProperty
            , String messageTextProperty, String messageSeverityProperty
            , String conversorId, String ... validatorIds);
    
    public abstract Object get(String property);
    public abstract Class getType(String property);
    public abstract void set(String property, Object value);
    public abstract Object invoke(String method, Object ... args);
    
   public abstract void updateViews(String ... viewUpdateIds) 
           throws ConstraintException;
   
   public abstract void updateBeans(String ... viewExecuteIds)
           throws ConstraintException;
    
}
