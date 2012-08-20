package ono.leo.com.jvcontroller.bean.access;

import java.lang.reflect.Method;

/**
 * Implementation of BeanInstanceAccessorInterface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (15/08/2012 09:49)
 */
public class BeanInstanceSingleAccessor 
    implements BeanInstanceAccessorInterface {

    @Override
    public Object get(Object bean, String property) throws Exception {
        String provName = property.substring(0, 1).toUpperCase()
                + property.substring(1, property.length());

        String getMethodName = "";
        Method method = null;
        try {
            getMethodName = "get" + provName;
            method = bean.getClass().getMethod(getMethodName);
        } catch (NoSuchMethodException ex) {
            try {
                getMethodName = "is" + provName;
                method = bean.getClass().getMethod(getMethodName);
            } catch (NoSuchMethodException ex2) {
                throw new Exception("Getter method for \"" + property
                        + "\" property in " + bean.getClass().getName() 
                        + " class not found !", ex2);
            }
        }
        try {
            bean = method.invoke(bean);
        }
        catch (Exception e) {
            throw (Exception) (e.getCause()==null ? e : e.getCause());
        }
        return bean;
    }
    
    @Override
    public void set(Object bean, String property, Object value) 
            throws Exception {
        
        String provName = property.substring(0, 1).toUpperCase()
                + property.substring(1, property.length());

        String setMethodName = "set" + provName;
        Method method = null;
        for (Method m : bean.getClass().getMethods()) {
            if (m.getName().equals(setMethodName)) {
                method = m;
                break;
            }
        }
        if (method == null) {
                throw new Exception("Getter method for \"" + property
                        + "\" property in " + bean.getClass().getName() 
                        + " class not found !");
        }
        // Se o(s) argumento(s) nao estiver(em) de acordo, lanca
        // java.lang.IllegalArgumentException: argument type mismatch
        try {
            method.invoke(bean, value);
        }
        catch (Exception e) {
            throw (Exception) (e.getCause()==null ? e : e.getCause());
        }
    }

    @Override
    public Object invoke(Object bean, String methodName, Object ... values) 
            throws Exception {
        
        // TODO must get the correct method between overloaded methods
        Method method = null;
        for (Method m : bean.getClass().getMethods()) {
            if (m.getName().equals(methodName) 
                    && m.getParameterTypes().length == values.length) {
                
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new Exception("Method \"" + methodName 
                    + "\" in " + bean.getClass().getName() 
                    + " class not found !");
        }
        // Se o(s) argumento(s) nao estiver(em) de acordo, lanca
        // java.lang.IllegalArgumentException: argument type mismatch
        Object ret = null;
        try {
            ret = method.invoke(bean, values);
        }
        catch (Exception e) {
            throw (Exception) (e.getCause()==null ? e : e.getCause());
        }
        return ret;
    }
    
}
