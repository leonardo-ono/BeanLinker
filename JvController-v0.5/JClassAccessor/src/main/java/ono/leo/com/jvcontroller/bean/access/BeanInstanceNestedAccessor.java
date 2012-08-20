package ono.leo.com.jvcontroller.bean.access;

/**
 * BeanInstanceNestedAccessor.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (15/08/2012 09:52)
 */
public class BeanInstanceNestedAccessor extends BeanInstanceSingleAccessor {
    
    @Override
    public void set(Object bean, String propertyName, Object value) 
            throws Exception {
        
        if (bean == null) {
            throw new NullPointerException("Argument bean is null !");
        }
        Object sourceBean = bean;
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                super.set(bean, property, value);
            } 
            else {
                bean = super.get(bean, property);
                if (bean == null) {
                    throwNullPointerException(
                            sourceBean, properties, property, i);
                }
            }
        }
    }

    @Override
    public Object get(Object bean, String propertyName) throws Exception {

        if (bean == null) {
            return null;
        }
        String[] properties = propertyName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            bean = super.get(bean, property);
            if (bean == null) {
                return null;
            }
        }
        return bean;
    }

    @Override
    public Object invoke(Object bean, String methodName, Object... values) 
            throws Exception {
        
        if (bean == null) {
            throw new NullPointerException("Argument bean is null !");
        }
        Object sourceBean = bean;
        String[] properties = methodName.split("\\.");
        for (int i=0; i<properties.length; i++) {
            String property = properties[i];
            if (i == (properties.length - 1)) {
                return super.invoke(bean, property, values);
            } 
            else {
                bean = super.get(bean, property);
                if (bean == null) {
                    throwNullPointerException(
                            sourceBean, properties, property, i);
                }
            }
        }
        return null;
    }
    
    private void throwNullPointerException(Object sourceBean
            , String[] properties, String property, int i) 
            throws NullPointerException {
        
        String propertyName = "";
        for (int i2=0; i2<properties.length; i2++) {
            if (i == i2) {
                propertyName += "[" + properties[i2] + "].";
                continue;
            }
            propertyName += properties[i2] + ".";
        }
        propertyName = propertyName.substring(0, propertyName.length()-1);
        throw new NullPointerException("Property " + property 
                + " of " + sourceBean.getClass().getName() 
                + "." + propertyName + " is null !");
    }
    
}
