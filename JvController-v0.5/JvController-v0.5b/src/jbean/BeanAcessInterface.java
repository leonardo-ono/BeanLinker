package jbean;

/**
 * Bean Interface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public interface BeanAcessInterface {
   
    public void set(Object bean, String propertyName, Object value) throws Exception; 
    public Object get(Object bean, String propertyName) throws Exception;
    public Object invoke(Object bean, String methodName, Object ... value) throws Exception;
    public Class getType(Object bean, String propertyName) throws Exception;
    public boolean hasGetter(Object bean, String propertyName) throws Exception;
    public boolean hasSetter(Object bean, String propertyName) throws Exception;
    public boolean hasMethod(Object bean, String methodName) throws Exception;
    
}
