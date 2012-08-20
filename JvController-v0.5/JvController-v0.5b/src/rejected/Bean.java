package rejected;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import jbean.BeanAcessInterface;

/**
 * Implementacao de BeanInterface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class Bean implements BeanAcessInterface {

    private static BeanAcessInterface bean;
    private Map<Class, Class> primitiveTypes = new HashMap<Class, Class>();
    
    public synchronized static BeanAcessInterface getInstance() {
        if (bean == null) {
            bean = new Bean();
        }
        return bean;
    }

    private Bean() {
        primitiveTypes.put(Byte.class, byte.class);
        primitiveTypes.put(Short.class, short.class);
        primitiveTypes.put(Integer.class, int.class);
        primitiveTypes.put(Long.class, long.class);
        primitiveTypes.put(Float.class, float.class);
        primitiveTypes.put(Double.class, double.class);
        primitiveTypes.put(Boolean.class, boolean.class);
        primitiveTypes.put(Character.class, char.class);
    }

    @Override
    public void set(Object bean, String propertyName, Object value)
            throws Exception {

        int count = 0;
        String nameProv = null;
        String methodName = null;
        Method method = null;
        String[] properties = propertyName.split("\\.");
        for (String property : properties) {
            nameProv = property.substring(0, 1).toUpperCase()
                    + property.substring(1, property.length());

            count++;
            if (count == properties.length) {
                break;
            }
            try {
                methodName = "get" + nameProv;
                method = bean.getClass().getMethod(methodName);
            } 
            catch (NoSuchMethodException ex) {
                try {
                    methodName = "is" + nameProv;
                    method = bean.getClass().getMethod(methodName);
                } catch (NoSuchMethodException ex2) {
                    throw new Exception("Property \"" + propertyName
                            + "\" not found !", ex2);
                }
            }
            bean = method.invoke(bean);
        }
        try {
            methodName = "set" + nameProv;
            method = bean.getClass().getMethod(methodName, value.getClass());
        } catch (NoSuchMethodException ex) {
            try {
                Class primitiveType = primitiveTypes.get(value.getClass());
                if (primitiveType == null) {
                    throw new NoSuchMethodException();
                }
                method = bean.getClass().getMethod(methodName, primitiveType);
            } 
            catch (NoSuchMethodException ex2) {
                try {
                    for (Method m : bean.getClass().getMethods()) {
                        if (m.getName().equals(methodName)) {
                            method = m;
                            break;
                        }
                    }
                    if (method == null) {
                        throw new NoSuchMethodException();
                    }
                } 
                catch (NoSuchMethodException ex3) {
                    throw new Exception("Setter method for \"" + propertyName
                            + "\" property not found !", ex3);
                }
            }
        }
        // Se o(s) argumento(s) nao estiver(em) de acordo, lanca
        // java.lang.IllegalArgumentException: argument type mismatch
        method.invoke(bean, value);
    }

    @Override
    public Object get(Object bean, String propertyName) throws Exception {
        String[] properties = propertyName.split("\\.");
        for (String property : properties) {
            String nameProv = property.substring(0, 1).toUpperCase()
                    + property.substring(1, property.length());

            String getMethodName = "";
            Method method = null;
            try {
                getMethodName = "get" + nameProv;
                method = bean.getClass().getMethod(getMethodName);
            } catch (NoSuchMethodException ex) {
                try {
                    getMethodName = "is" + nameProv;
                    method = bean.getClass().getMethod(getMethodName);
                } catch (NoSuchMethodException ex2) {
                    throw new Exception("Getter method for \"" + propertyName
                            + "\" property not found !", ex2);
                }
            }
            bean = method.invoke(bean);
        }
        return bean;
    }

    @Override
    public Object invoke(Object bean, String methodName, Object... values) 
            throws Exception {
        
        int count = 0;
        String nameProv = null;
        String provMethodName = null;
        Method method = null;
        String[] properties = methodName.split("\\.");
        for (String property : properties) {
            nameProv = property.substring(0, 1).toUpperCase()
                    + property.substring(1, property.length());

            count++;
            if (count == properties.length) {
                nameProv = property;
                break;
            }
            try {
                provMethodName = "get" + nameProv;
                method = bean.getClass().getMethod(provMethodName);
            } 
            catch (NoSuchMethodException ex) {
                try {
                    provMethodName = "is" + nameProv;
                    method = bean.getClass().getMethod(provMethodName);
                } catch (NoSuchMethodException ex2) {
                    throw new Exception("Property \"" + methodName
                            + "\" not found !", ex2);
                }
            }
            bean = method.invoke(bean);
        }
        provMethodName = nameProv;
        for (Method m : bean.getClass().getMethods()) {
            if (m.getName().equals(provMethodName)) {
                method = m;
                break;
            }
        }
        // Se o(s) argumento(s) nao estiver(em) de acordo, lanca
        // java.lang.IllegalArgumentException: argument type mismatch
        return method.invoke(bean, values);
    }

    @Override
    public Class getType(Object bean, String propertyName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean hasGetter(Object bean, String propertyName) throws Exception {
        String[] properties = propertyName.split("\\.");
        int count=0;
        for (String property : properties) {
            String nameProv = property.substring(0, 1).toUpperCase()
                    + property.substring(1, property.length());

            String getMethodName = "";
            Method method = null;
            try {
                getMethodName = "get" + nameProv;
                method = bean.getClass().getMethod(getMethodName);
            } catch (NoSuchMethodException ex) {
                try {
                    getMethodName = "is" + nameProv;
                    method = bean.getClass().getMethod(getMethodName);
                } catch (NoSuchMethodException ex2) {
                    return false;
                }
            }
            if (++count == properties.length) {
                break;
            }
            bean = method.invoke(bean);
        }
        return true;
    }

    @Override
    public boolean hasSetter(Object bean, String propertyName) throws Exception {
        int count = 0;
        String nameProv = null;
        String methodName = null;
        Method method = null;
        String[] properties = propertyName.split("\\.");
        for (String property : properties) {
            nameProv = property.substring(0, 1).toUpperCase()
                    + property.substring(1, property.length());

            count++;
            if (count == properties.length) {
                break;
            }
            try {
                methodName = "get" + nameProv;
                method = bean.getClass().getMethod(methodName);
            } 
            catch (NoSuchMethodException ex) {
                try {
                    methodName = "is" + nameProv;
                    method = bean.getClass().getMethod(methodName);
                } catch (NoSuchMethodException ex2) {
                    return false;
                }
            }
            bean = method.invoke(bean);
        }
        methodName = "set" + nameProv;
        for (Method m : bean.getClass().getMethods()) {
            if (m.getName().equals(methodName)) {
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
