package ono.leo.com.jvcontroller.bean.access;

/**
 * BeanClassNestedAccessor.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 17:15)
 */
public class BeanClassNestedAccessor extends BeanClassSingleAccessor {
    
    private static BeanClassNestedAccessor instance 
            = new BeanClassNestedAccessor();

    public static BeanClassNestedAccessor getInstance() {
        return instance;
    }

    private BeanClassNestedAccessor() {
    }
    
    @Override
    public Class getType(Class beanClass, String propertyName) 
            throws Exception {
        
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.getType(beanClass, property);
            } 
            else {
                beanClass = super.getType(beanClass, property);
            }
        }
        return null;
    }
    
    @Override
    public Class getGenericType(Class beanClass, String propertyName) 
            throws Exception {
        
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.getGenericType(beanClass, property);
            } 
            else {
                beanClass = super.getType(beanClass, property);
            }
        }
        return null;
    }
    
    @Override
    public boolean hasGetter(Class beanClass, String propertyName) 
            throws Exception {
        
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.hasGetter(beanClass, property);
            } 
            else {
                beanClass = super.getType(beanClass, property);
            }
        }
        return false;
    }

    @Override
    public boolean hasSetter(Class beanClass, String propertyName) 
            throws Exception {
        
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.hasSetter(beanClass, property);
            } 
            else {
                beanClass = super.getType(beanClass, property);
            }
        }
        return false;
    }

    @Override
    public boolean hasMethod(Class beanClass, String methodName) 
            throws Exception {
        
        String[] properties = methodName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.hasMethod(beanClass, property);
            } 
            else {
                beanClass = super.getType(beanClass, property);
            }
        }
        return false;
    }
    
}
