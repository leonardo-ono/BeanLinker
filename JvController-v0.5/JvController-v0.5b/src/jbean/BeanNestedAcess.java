package jbean;

/**
 * Implementacao de BeanNestedAcess.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.00.00
 */
public class BeanNestedAcess extends BeanSingleAcess {
    
    @Override
    public void set(Object bean, String propertyName, Object value) 
            throws Exception {
        
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                super.set(bean, property, value);
            } 
            else {
                bean = super.get(bean, property);
            }
        }
    }

    @Override
    public Object get(Object bean, String propertyName) throws Exception {
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            bean = super.get(bean, property);
        }
        return bean;
    }

    @Override
    public Object invoke(Object bean, String methodName, Object... values) 
            throws Exception {
        
        String[] properties = methodName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.invoke(bean, property, values);
            } 
            else {
                bean = super.get(bean, property);
            }
        }
        return null;
    }

    @Override
    public Class getType(Object bean, String propertyName) throws Exception {
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.getType(bean, propertyName);
            } 
            else {
                bean = super.get(bean, property);
            }
        }
        return null;
    }

    @Override
    public boolean hasGetter(Object bean, String propertyName) 
            throws Exception {
        
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.hasGetter(bean, propertyName);
            } 
            else {
                bean = super.get(bean, property);
            }
        }
        return false;
    }

    @Override
    public boolean hasSetter(Object bean, String propertyName) 
            throws Exception {
        
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.hasSetter(bean, propertyName);
            } 
            else {
                bean = super.get(bean, property);
            }
        }
        return false;
    }

    @Override
    public boolean hasMethod(Object bean, String methodName) throws Exception {
        String[] properties = methodName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.hasMethod(bean, methodName);
            } 
            else {
                bean = super.get(bean, property);
            }
        }
        return false;
    }
    
}
