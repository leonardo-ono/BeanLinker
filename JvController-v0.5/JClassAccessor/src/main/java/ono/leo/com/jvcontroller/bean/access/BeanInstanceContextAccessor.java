package ono.leo.com.jvcontroller.bean.access;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanInstanceContextAccessor.
 * Virtual properties was implemented in this class.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (15/08/2012 10:02)
 */
public class BeanInstanceContextAccessor extends BeanInstanceNestedAccessor {

    private BeanClassNestedAccessor bcna 
            = BeanClassNestedAccessor.getInstance();
    
    private Map<String, Object> beans = new HashMap<String, Object>();
    
    private Map<String, Object> virtualProperties 
            = new HashMap<String, Object>();

    private static String validBeanIdCharacters 
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public BeanInstanceContextAccessor() {
    }

    public String getBeanId(Object beanObj) {
        for (String key : beans.keySet()) {
            Object obj = beans.get(key);
            if (obj.equals(beanObj)) {
                return key;
            }
        }
        return null;
    }
    
    Map<String, Object> getBeans() {
        return beans;
    }

    Map<String, Object> getVirtualProperties() {
        return virtualProperties;
    }
            
    public void addBean(String beanId, Object bean) throws Exception {
        if (beanId == null) {
            throw new NullPointerException("Bean id must not be null !");
        }
        if (bean == null) {
            throw new NullPointerException("Bean instance must not be null !");
        }
        beanId = beanId.trim();
        if (beanId.length() == 0) {
            throw new NullPointerException("Bean id must not be empty !");
        }
        if (Character.isUpperCase(beanId.charAt(0))) {
            throw new Exception("Bean id must start with lower letter !");
        }
        if (!Character.isLetter(beanId.charAt(0))) {
            throw new Exception("Bean id must start with a letter !");
        }
        for (int i = 0; i < beanId.length(); i++) {
            String c = String.valueOf(beanId.charAt(i));
            if (!validBeanIdCharacters.contains(c)) {
                throw new Exception(
                    "Bean id contains invalid character '" + c + "' !");
            }
        }
        if (beans.containsKey(beanId)) {
            throw new Exception("Bean id \"" + beanId 
                    + "\" already found in this context !");
        }
        beans.put(beanId, bean);
    }

    public void removeBean(String beanId) {
        beans.remove(beanId);
    }

    public void set(String propertyName, Object value) 
            throws Exception {
        
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (bean == null) {
            throw new Exception(
                "Bean id \"" + property + "\" not found in this context !");
        }
        propertyName = propertyName.substring(property.length() + 1);
        // Set in virtual property if it does not exist in real instance
        int lastPropertyPos = propertyName.lastIndexOf(".");
        String nsProperty = "";
        if (lastPropertyPos >= 0) {
            nsProperty = propertyName.substring(0, lastPropertyPos);
        }
        else {
            nsProperty = propertyName;
        }
        if ( (bcna.hasGetter(bean.getClass(), nsProperty)
            || (lastPropertyPos < 0) )
                && !bcna.hasGetter(bean.getClass(), propertyName)
                && !bcna.hasSetter(bean.getClass(), propertyName)) {
            
            propertyName = property + "." + propertyName;
            virtualProperties.put(propertyName, value);
        }
        // Otherwise, set int the real instance
        else {
            super.set(bean, propertyName, value);
        }
    }

    public Object get(String propertyName) throws Exception {
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (bean == null) {
            throw new Exception(
                "Bean id \"" + property + "\" not found in this context !");
        }
        if (properties.length == 1) {
            return bean;
        }
        String p = properties[1];
        int i = 0;
        for (i=2; i<properties.length; i++) {
            if (bcna.hasGetter(bean.getClass(), p)) {
                p += "." + properties[i];
            }
            else {
                break;
            }
        }
        if (!bcna.hasGetter(bean.getClass(), p)
                && !bcna.hasSetter(bean.getClass(), p)) {
            
            propertyName = property + "." + p;
            if (!virtualProperties.containsKey(propertyName)) {
                throw new Exception("Property \"" + propertyName
                        + "\" in " + bean.getClass().getName() 
                        + " class not found !");
            }
            bean = virtualProperties.get(propertyName);
            p = "";
            for (int i2=i; i2<properties.length; i2++) {
                p += properties[i2] + ".";
            }
            if (p.length() == 0) {
                return bean;
            }
            p = p.substring(0, p.length()-1);
            return super.get(bean, p);
        }
        else {
            propertyName = propertyName.substring(property.length() + 1);
            return super.get(bean, propertyName);
        }
    }

    public Object invoke(String methodName, Object... values) 
            throws Exception {
        
        String properties[] = methodName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (bean == null) {
            throw new Exception(
                "Bean id \"" + property + "\" not found in this context !");
        }
        String p = properties[1];
        int i = 0;
        for (i=2; i<properties.length; i++) {
            if (bcna.hasGetter(bean.getClass(), p)) {
                p += "." + properties[i];
            }
            else {
                break;
            }
        }
        if (!bcna.hasMethod(bean.getClass(), p)) {
            
            methodName = property + "." + p;
            if (!virtualProperties.containsKey(methodName)) {
                throw new Exception("Property \"" + methodName
                        + "\" in " + bean.getClass().getName() 
                        + " class not found !");
            }
            bean = virtualProperties.get(methodName);
            p = "";
            for (int i2=i; i2<properties.length; i2++) {
                p += properties[i2] + ".";
            }
            if (p.length() == 0) {
                return bean;
            }
            p = p.substring(0, p.length()-1);
            return super.invoke(bean, p, values);
        }
        else {
            methodName = methodName.substring(property.length() + 1);
            return super.invoke(bean, methodName, values);
        }
    }

    public Class getType(String propertyName) throws Exception {
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (bean == null) {
            throw new Exception(
                "Bean id \"" + property + "\" not found in this context !");
        }
        String p = properties[1];
        int i = 0;
        for (i=2; i<properties.length; i++) {
            if (bcna.hasGetter(bean.getClass(), p)) {
                p += "." + properties[i];
            }
            else {
                break;
            }
        }
        if (!bcna.hasGetter(bean.getClass(), p)
                && !bcna.hasSetter(bean.getClass(), p)) {
            
            propertyName = property + "." + p;
            if (!virtualProperties.containsKey(propertyName)) {
                throw new Exception("Property \"" + propertyName
                        + "\" in " + bean.getClass().getName() 
                        + " class not found !");
            }
            bean = virtualProperties.get(propertyName);
            p = "";
            for (int i2=i; i2<properties.length; i2++) {
                p += properties[i2] + ".";
            }
            if (p.length() == 0) {
                return bean.getClass();
            }
            p = p.substring(0, p.length()-1);
            return bcna.getType(bean.getClass(), p);
        }
        else {
            propertyName = propertyName.substring(property.length() + 1);
            return bcna.getType(bean.getClass(), propertyName);
        }
    }
    
    public Class getGenericType(String propertyName) throws Exception {
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (bean == null) {
            throw new Exception(
                "Bean id \"" + property + "\" not found in this context !");
        }
        String p = properties[1];
        int i = 0;
        for (i=2; i<properties.length; i++) {
            if (bcna.hasGetter(bean.getClass(), p)) {
                p += "." + properties[i];
            }
            else {
                break;
            }
        }
        if (!bcna.hasGetter(bean.getClass(), p)
                && !bcna.hasSetter(bean.getClass(), p)) {
            
            propertyName = property + "." + p;
            if (!virtualProperties.containsKey(propertyName)) {
                throw new Exception("Property \"" + propertyName
                        + "\" in " + bean.getClass().getName() 
                        + " class not found !");
            }
            bean = virtualProperties.get(propertyName);
            p = "";
            for (int i2=i; i2<properties.length; i2++) {
                p += properties[i2] + ".";
            }
            if (p.length() == 0) {
                //return null;
                // TODO
                throw new Exception("Not is possible to detect "
                        + "generic type of dynamic properties !");
            }
            p = p.substring(0, p.length()-1);
            return bcna.getGenericType(bean.getClass(), p);
        }
        else {
            propertyName = propertyName.substring(property.length() + 1);
            return bcna.getGenericType(bean.getClass(), propertyName);
        }
    }
    
    public boolean hasGetter(String propertyName) 
            throws Exception {
        
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (bean == null) {
            throw new Exception(
                "Bean id \"" + property + "\" not found in this context !");
        }
        String p = properties[1];
        int i = 0;
        for (i=2; i<properties.length; i++) {
            if (bcna.hasGetter(bean.getClass(), p)) {
                p += "." + properties[i];
            }
            else {
                break;
            }
        }
        if (!bcna.hasGetter(bean.getClass(), p)
                && !bcna.hasSetter(bean.getClass(), p)) {
            
            propertyName = property + "." + p;
            if (!virtualProperties.containsKey(propertyName)) {
                throw new Exception("Property \"" + propertyName
                        + "\" in " + bean.getClass().getName() 
                        + " class not found !");
            }
            bean = virtualProperties.get(propertyName);
            p = "";
            for (int i2=i; i2<properties.length; i2++) {
                p += properties[i2] + ".";
            }
            if (p.length() == 0) {
                return false;
            }
            p = p.substring(0, p.length()-1);
            return bcna.hasGetter(bean.getClass(), p);
        }
        else {
            propertyName = propertyName.substring(property.length() + 1);
            return bcna.hasGetter(bean.getClass(), propertyName);
        }
    }

    public boolean hasSetter(String propertyName) 
            throws Exception {
        
        String properties[] = propertyName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (bean == null) {
            throw new Exception(
                "Bean id \"" + property + "\" not found in this context !");
        }
        String p = properties[1];
        int i = 0;
        for (i=2; i<properties.length; i++) {
            if (bcna.hasGetter(bean.getClass(), p)) {
                p += "." + properties[i];
            }
            else {
                break;
            }
        }
        if (!bcna.hasGetter(bean.getClass(), p)
                && !bcna.hasSetter(bean.getClass(), p)) {
            
            propertyName = property + "." + p;
            if (!virtualProperties.containsKey(propertyName)) {
                throw new Exception("Property \"" + propertyName
                        + "\" in " + bean.getClass().getName() 
                        + " class not found !");
            }
            bean = virtualProperties.get(propertyName);
            p = "";
            for (int i2=i; i2<properties.length; i2++) {
                p += properties[i2] + ".";
            }
            if (p.length() == 0) {
                return false;
            }
            p = p.substring(0, p.length()-1);
            return bcna.hasSetter(bean.getClass(), p);
        }
        else {
            propertyName = propertyName.substring(property.length() + 1);
            return bcna.hasSetter(bean.getClass(), propertyName);
        }
    }

    public boolean hasMethod(String methodName) throws Exception {
        String properties[] = methodName.split("\\.");
        String property = properties[0];
        Object bean = beans.get(property);
        if (bean == null) {
            throw new Exception(
                "Bean id \"" + property + "\" not found in this context !");
        }
        String p = properties[1];
        int i = 0;
        for (i=2; i<properties.length; i++) {
            if (bcna.hasGetter(bean.getClass(), p)) {
                p += "." + properties[i];
            }
            else {
                break;
            }
        }
        if (!bcna.hasGetter(bean.getClass(), p)
                && !bcna.hasSetter(bean.getClass(), p)) {
            
            methodName = property + "." + p;
            if (!virtualProperties.containsKey(methodName)) {
                throw new Exception("Property \"" + methodName
                        + "\" in " + bean.getClass().getName() 
                        + " class not found !");
            }
            bean = virtualProperties.get(methodName);
            p = "";
            for (int i2=i; i2<properties.length; i2++) {
                p += properties[i2] + ".";
            }
            if (p.length() == 0) {
                return false;
            }
            p = p.substring(0, p.length()-1);
            return bcna.hasMethod(bean.getClass(), p);
        }
        else {
            methodName = methodName.substring(property.length() + 1);
            return bcna.hasMethod(bean.getClass(), methodName);
        }
    }    
    
}
