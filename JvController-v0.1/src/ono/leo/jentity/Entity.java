package ono.leo.jentity;

import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * @author leonardo
 */
public class Entity {
    
    public static void set(Object entity, String propertyName, Object value) {
        
        if (propertyName.contains(".")) {
            String entityPropertyName 
                    = propertyName.substring(0, propertyName.lastIndexOf('.'));
            
            propertyName = propertyName.substring(
                    propertyName.lastIndexOf('.')+1, propertyName.length());
            
            entity = get(entity, entityPropertyName);
        }
        
        String setterMethodName = "set" 
                + propertyName.substring(0, 1).toUpperCase() 
                + propertyName.substring(1);
        
        Method setterMethod = null;
        for (Method method : entity.getClass().getMethods()) {
            if (method.getName().equals(setterMethodName)
                    && method.getParameterTypes().length==1) {
                
                setterMethod = method;
                continue;
            }
        }
        
        if (setterMethod == null)
            throw new RuntimeException("Method " + setterMethodName 
                    + " was not found in " + entity.getClass().getSimpleName() 
                    + " entity class !");
        
        try {
            setterMethod.invoke(entity, value);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public static Object get(Object entity, String propertyName) {
        
        boolean hasMoreProperty = false;
        String nextProperty = "";
        
        // Se for do tipo Map, invoca map.get(propertyName)
        while (entity instanceof Map) {
            
            if (propertyName.contains(".")) {
                nextProperty = propertyName.substring(
                        propertyName.indexOf('.')+1, propertyName.length());

                propertyName = propertyName
                        .substring(0, propertyName.indexOf('.'));
                
                entity = ((Map) entity).get(propertyName);
                
                propertyName = nextProperty;
            }
            else {
                return ((Map) entity).get(propertyName);
            }
        }
        
        
        if (propertyName.contains(".")) {
            nextProperty = propertyName.substring(
                    propertyName.indexOf('.')+1, propertyName.length());
            
            propertyName = propertyName.substring(0, propertyName.indexOf('.'));
            hasMoreProperty = true;
        }
        
        String getterMethodName = "get" 
                + propertyName.substring(0, 1).toUpperCase() 
                + propertyName.substring(1);
        
        String isMethodName = "is" 
                + propertyName.substring(0, 1).toUpperCase() 
                + propertyName.substring(1);
        
        Method getterMethod = null;
        for (Method method : entity.getClass().getMethods()) {
            if ( (method.getName().equals(getterMethodName)
                    || method.getName().equals(isMethodName))
                    && method.getParameterTypes().length==0 ) {
                
                getterMethod = method;
                continue;
            }
        }
        
        if (getterMethod == null) return null;
        /*
            throw new RuntimeException("Method " + getterMethodName 
                    + "/" + isMethodName
                    + " was not found in " + entity.getClass().getSimpleName() 
                    + " entity class !");
         * 
         */
        
        Object retObj = null;
        try {
            retObj = getterMethod.invoke(entity);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
        // Se tiver mais propriedades, invoca o proximo
        if (retObj != null && hasMoreProperty) {
            retObj = get(retObj, nextProperty);
        }
        
        return retObj;
    }

    public static Object invoke(
            Object entity, String methodName, Object ... value) {
        
        if (methodName.contains(".")) {
            String entityPropertyName 
                    = methodName.substring(0, methodName.lastIndexOf('.'));
            
            methodName = methodName.substring(
                    methodName.lastIndexOf('.')+1, methodName.length());
            
            entity = get(entity, entityPropertyName);
        }
        
        Method method = null;
        for (Method m : entity.getClass().getMethods()) {
            if (m.getName().equals(methodName)
                    && m.getParameterTypes().length==value.length) {
                
                method = m;
                continue;
            }
        }
        
        if (method == null)
            throw new RuntimeException("Method " + methodName 
                    + " was not found in " + entity.getClass().getSimpleName() 
                    + " entity class !");
        
        Object ret = null;
        try {
            ret = method.invoke(entity, value);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        
        return ret;
    }
    
    public static Class getType(Object entity, String propertyName) {
        
        boolean hasMoreProperty = false;
        String nextProperty = "";
        
        // Se for do tipo Map, invoca map.get(propertyName)
        while (entity instanceof Map) {
            
            if (propertyName.contains(".")) {
                nextProperty = propertyName.substring(
                        propertyName.indexOf('.')+1, propertyName.length());

                propertyName = propertyName
                        .substring(0, propertyName.indexOf('.'));
                
                entity = ((Map) entity).get(propertyName);
                
                propertyName = nextProperty;
            }
            else {
                return entity.getClass();
            }
        }
        
        
        if (propertyName.contains(".")) {
            nextProperty = propertyName.substring(
                    propertyName.indexOf('.')+1, propertyName.length());
            
            propertyName = propertyName.substring(0, propertyName.indexOf('.'));
            hasMoreProperty = true;
        }
        
        String getterMethodName = "get" 
                + propertyName.substring(0, 1).toUpperCase() 
                + propertyName.substring(1);
        
        String isMethodName = "is" 
                + propertyName.substring(0, 1).toUpperCase() 
                + propertyName.substring(1);
        
        Method getterMethod = null;
        for (Method method : entity.getClass().getMethods()) {
            if ( (method.getName().equals(getterMethodName)
                    || method.getName().equals(isMethodName))
                    && method.getParameterTypes().length==0 ) {
                
                getterMethod = method;
                continue;
            }
        }
        if (getterMethod == null) return null;
        /*
            throw new RuntimeException("Method " + getterMethodName 
                    + "/" + isMethodName
                    + " was not found in " + entity.getClass().getSimpleName() 
                    + " entity class !");
         * 
         */
        if (hasMoreProperty) {
        }
        
        return getterMethod.getReturnType();
    }

    public static boolean hasSetter(Object entity, String propertyName) {
        if (propertyName.contains(".")) {
            String entityPropertyName 
                    = propertyName.substring(0, propertyName.lastIndexOf('.'));
            
            propertyName = propertyName.substring(
                    propertyName.lastIndexOf('.')+1, propertyName.length());
            
            entity = get(entity, entityPropertyName);
        }
        
        String setterMethodName = "set" 
                + propertyName.substring(0, 1).toUpperCase() 
                + propertyName.substring(1);
        
        Method setterMethod = null;
        for (Method method : entity.getClass().getMethods()) {
            if (method.getName().equals(setterMethodName)
                    && method.getParameterTypes().length==1) {
                
                setterMethod = method;
                continue;
            }
        }
        
        return (setterMethod != null);
    }
    
}
