package ono.leo.jvcontroller.core;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * JvControllerClassContext.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 13:05)
 */
public class JvControllerClassContext {
    
    private static JvControllerClassContext instance 
            = new JvControllerClassContext();
    
    private Set<String> classAliases = new HashSet<String>();
    
    // key=obj.getClass().getName() value=classAlias
    private Map<String, String> classNames = new HashMap<String, String>();

    private Map<String, Class> viewClasses = new HashMap<String, Class>();
    private Map<String, Class> controllerClasses = new HashMap<String, Class>();
    private Map<String, Class> modelClasses = new HashMap<String, Class>();
    private Map<String, Class> conversorClasses = new HashMap<String, Class>();
    private Map<String, Class> validatorClasses = new HashMap<String, Class>();

    private static String validClassAliasCharacters 
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    
    private JvControllerClassContext() {
    }

    public static JvControllerClassContext getInstance() {
        return instance;
    }

    public Map<String, Class> getModelClasses() {
        return modelClasses;
    }

    public Map<String, Class> getViewClasses() {
        return viewClasses;
    }

    public Map<String, Class> getControllerClasses() {
        return controllerClasses;
    }

    public Map<String, Class> getConversorClasses() {
        return conversorClasses;
    }

    public Map<String, Class> getValidatorClasses() {
        return validatorClasses;
    }

    public Map<String, String> getClassNames() {
        return classNames;
    }
    
    private void registerBeanClass(String classAlias, String className
            , Map classes) throws Exception {
        
        if (classAlias == null) {
            throw new NullPointerException("Class alias must not be null !");
        }
        classAlias = classAlias.trim();
        if (classAlias.length() == 0) {
            throw new NullPointerException("Class alias must not be empty !");
        }
        if (Character.isLowerCase(classAlias.charAt(0))) {
            throw new Exception("Class alias must start with upper letter !");
        }
        if (!Character.isLetter(classAlias.charAt(0))) {
            throw new Exception("Class alias must start with a letter !");
        }
        for (int i = 0; i < classAlias.length(); i++) {
            String c = String.valueOf(classAlias.charAt(i));
            if (!validClassAliasCharacters.contains(c)) {
                throw new Exception(
                    "Class alias contains invalid character '" + c + "' !");
            }
        }
        if (classAliases.contains(classAlias)) {
            throw new Exception("Class alias \"" 
                    + classAlias + "\" already registered !");
        }
        Class beanClass = Class.forName(className);
        checkExistNoArgumentConstructor(beanClass);
        classes.put(classAlias, beanClass);
        classAliases.add(classAlias);
        classNames.put(beanClass.getName(), classAlias);
    }
    
    private void checkExistNoArgumentConstructor(Class c) throws Exception{
        for (Constructor constructor : c.getConstructors()) {
            if (constructor.getParameterTypes().length == 0) {
                return;
            }
        }
        throw new Exception("Class " + c.getName() 
                + " does not have a non-argument constructor !");
    }
    
    public void registerViewClass(String classAlias, String className) 
            throws Exception {
        
        registerBeanClass(classAlias, className, viewClasses);
    }

    public void registerModelClass(String classAlias, String className) 
            throws Exception {
        
        registerBeanClass(classAlias, className, modelClasses);
    }

    public void registerControllerClass(String classAlias, String className) 
            throws Exception {
        
        registerBeanClass(classAlias, className, controllerClasses);
    }
    
    public void registerConversorClass(String classAlias, String className) 
            throws Exception {
        
        registerBeanClass(classAlias, className, conversorClasses);
    }

    public void registerValidatorClass(String classAlias, String className) 
            throws Exception {
        
        registerBeanClass(classAlias, className, validatorClasses);
    }

}
