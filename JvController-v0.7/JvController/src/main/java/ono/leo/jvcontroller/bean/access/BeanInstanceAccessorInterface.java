package ono.leo.jvcontroller.bean.access;

/**
 * BeanInstanceAccessorInterface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (15/08/2012 09:45)
 */
public interface BeanInstanceAccessorInterface {
   
    public void set(Object bean, String propertyName, Object value) throws Exception; 
    public Object get(Object bean, String propertyName) throws Exception;
    public Object invoke(Object bean, String methodName, Object ... value) throws Exception;
    
}
