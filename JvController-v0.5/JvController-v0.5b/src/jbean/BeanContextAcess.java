package jbean;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanContextAcess.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class BeanContextAcess extends BeanNestedAcess {

    private Map<String, Object> beans = new HashMap<String, Object>();

    public Map<String, Object> getBeans() {
        return beans;
    }
            
    public void addBean(String id, Object bean) {
        beans.put(id, bean);
    }

    public void removeBean(String id) {
        beans.remove(id);
    }

    public void set(String propertyName, Object value) 
            throws Exception {
        
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        propertyName = propertyName.substring(property.length() + 1);
        super.set(bean, propertyName, value);
    }

    public Object get(String propertyName) throws Exception {
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (properties.length == 1) {
            return bean;
        }
        propertyName = propertyName.substring(property.length() + 1);
        return super.get(bean, propertyName);
    }

    public Object invoke(String methodName, Object... values) 
            throws Exception {
        
        String properties[] = methodName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        methodName = methodName.substring(property.length() + 1);
        return super.invoke(bean, methodName, values);
    }

    public Class getType(String propertyName) throws Exception {
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        propertyName = propertyName.substring(property.length() + 1);
        return super.getType(bean, propertyName);
    }

    public boolean hasGetter(String propertyName) 
            throws Exception {
        
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        propertyName = propertyName.substring(property.length() + 1);
        return super.hasGetter(bean, propertyName);
    }

    public boolean hasSetter(String propertyName) 
            throws Exception {
        
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        propertyName = propertyName.substring(property.length() + 1);
        return super.hasSetter(bean, propertyName);
    }

    public boolean hasMethod(String methodName) throws Exception {
        String properties[] = methodName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        methodName = methodName.substring(property.length() + 1);
        return super.hasMethod(bean, methodName);
    }    
    
}
