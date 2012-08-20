package jbean2.core;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Implementacao de BeanSingleAcess.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class BeanSingleAcess implements BeanAcessInterface {

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
                        + "\" property not found !", ex2);
            }
        }
        bean = method.invoke(bean);
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
                    + "\" property not found !");
        }
        // Se o(s) argumento(s) nao estiver(em) de acordo, lanca
        // java.lang.IllegalArgumentException: argument type mismatch
        method.invoke(bean, value);
    }

    @Override
    public Object invoke(Object bean, String methodName, Object ... values) 
            throws Exception {
        
        Method method = null;
        for (Method m : bean.getClass().getMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        if (method == null) {
            throw new Exception("Method \"" + methodName + "\" not found !");
        }
        // Se o(s) argumento(s) nao estiver(em) de acordo, lanca
        // java.lang.IllegalArgumentException: argument type mismatch
        return method.invoke(bean, values);
    }
    
    @Override
    public boolean hasGetter(Object bean, String property) throws Exception {
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
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean hasSetter(Object bean, String property) throws Exception {
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
            return false;
        }
        return true;
    }
    
    @Override
    public boolean hasMethod(Object bean, String methodName) throws Exception {
        Method method = null;
        for (Method m : bean.getClass().getMethods()) {
            if (m.getName().equals(methodName)) {
                method = m;
                break;
            }
        }
        return (method != null);
    }
    
    @Override
    public Class getType(Object bean, String property) throws Exception {
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
                        + "\" property not found !", ex2);
            }
        }
        return method.getReturnType();
    }

    @Override
    public Class getCollectionType(Object bean, String property) throws Exception {
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
                       + "\" property not found !", ex2);
            }
        }
        Type returnType = method.getGenericReturnType();
        ParameterizedType pType = (ParameterizedType) returnType;
        Class c = (Class) pType.getActualTypeArguments()[0];
        return c;
    }
    
}
