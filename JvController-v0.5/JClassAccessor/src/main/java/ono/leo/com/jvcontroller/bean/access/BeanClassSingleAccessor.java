package ono.leo.com.jvcontroller.bean.access;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Implementacao de BeanClassAccessorInterface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class BeanClassSingleAccessor implements BeanClassAccessorInterface {

    @Override
    public boolean hasGetter(Class beanClass, String property) 
            throws Exception {
        
        if (beanClass == null 
                || property == null || property.trim().length()==0) {
            
            return false;
        }
        String provName = property.substring(0, 1).toUpperCase()
                + property.substring(1, property.length());

        String getMethodName = "";
        Method method = null;
        try {
            getMethodName = "get" + provName;
            method = beanClass.getMethod(getMethodName);
        } catch (NoSuchMethodException ex) {
            try {
                getMethodName = "is" + provName;
                method = beanClass.getMethod(getMethodName);
            } catch (NoSuchMethodException ex2) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean hasSetter(Class beanClass, String property) 
            throws Exception {
        
        if (beanClass == null) {
            return false;
        }
        String provName = property.substring(0, 1).toUpperCase()
                + property.substring(1, property.length());

        String setMethodName = "set" + provName;
        Method method = null;
        for (Method m : beanClass.getMethods()) {
            if (m.getName().equals(setMethodName)) {
                method = m;
                break;
            }
        }
        if (method == null) {
            return false;
        }
        return true;
    }
    
    @Override
    public boolean hasMethod(Class beanClass, String methodName) 
            throws Exception {
        
        if (beanClass == null) {
            return false;
        }
        Method method = null;
        for (Method m : beanClass.getMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        return (method != null);
    }
    
    @Override
    public Class getType(Class beanClass, String property) throws Exception {
        String provName = property.substring(0, 1).toUpperCase()
                + property.substring(1, property.length());

        if (beanClass == null) {
            return null;
        }
        String getMethodName = "";
        Method method = null;
        try {
            getMethodName = "get" + provName;
            method = beanClass.getMethod(getMethodName);
        } catch (NoSuchMethodException ex) {
            try {
                getMethodName = "is" + provName;
                method = beanClass.getMethod(getMethodName);
            } catch (NoSuchMethodException ex2) {
                throw new Exception("Getter method for \"" + property
                       + "\" property in " + beanClass.getName() 
                       + " class not found !", ex2);
            }
        }
        return method.getReturnType();
    }

    @Override
    public Class getGenericType(Class beanClass, String property) 
            throws Exception {
        
        if (beanClass == null) {
            return null;
        }
        String provName = property.substring(0, 1).toUpperCase()
                + property.substring(1, property.length());

        String getMethodName = "";
        Method method = null;
        try {
            getMethodName = "get" + provName;
            method = beanClass.getMethod(getMethodName);
        } catch (NoSuchMethodException ex) {
            try {
                getMethodName = "is" + provName;
                method = beanClass.getMethod(getMethodName);
            } catch (NoSuchMethodException ex2) {
                throw new Exception("Getter method for \"" + property
                        + "\" property in " + beanClass.getName() 
                        + " class not found !", ex2);
            }
        }
        Type returnType = method.getGenericReturnType();
        if (!(returnType instanceof ParameterizedType)) {
            return null;
        }
        ParameterizedType pType = (ParameterizedType) returnType;
        Class c = (Class) pType.getActualTypeArguments()[0];
        return c;
    }
    
}
