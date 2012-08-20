package ono.leo.com.jvcontroller.bean.access;

/**
 * BeanClassAccessorInterface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 16:58)
 */
public interface BeanClassAccessorInterface {
   
    public Class getType(Class bean, String propertyName) throws Exception;
    public Class getGenericType(Class bean, String propertyName) throws Exception;
    
    public boolean hasGetter(Class bean, String propertyName) throws Exception;
    public boolean hasSetter(Class bean, String propertyName) throws Exception;
    public boolean hasMethod(Class bean, String methodName) throws Exception;
    
}
