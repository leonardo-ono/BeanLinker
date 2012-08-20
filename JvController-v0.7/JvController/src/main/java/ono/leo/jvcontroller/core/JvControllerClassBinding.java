package ono.leo.jvcontroller.core;

import java.util.ArrayList;
import java.util.Collection;
import ono.leo.jvcontroller.bean.binding.ClassPropertyBinding;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import ono.leo.jvcontroller.bean.binding.ClassActionBinding;
import ono.leo.jvcontroller.bean.binding.ClassBeanBinding;
import ono.leo.jvcontroller.bean.binding.ClassBinding;
import ono.leo.jvcontroller.bean.binding.ClassCollectionBinding;

/**
 * JvControllerClassBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 15:52)
 */
public class JvControllerClassBinding {
    
    private static JvControllerClassBinding instance 
            = new JvControllerClassBinding();
    
    private Map<String, ClassPropertyBinding> propertyBindings 
            = new HashMap<String, ClassPropertyBinding>();

    private Map<String, ClassBeanBinding> beanBindings 
            = new HashMap<String, ClassBeanBinding>();

    private Map<String, ClassCollectionBinding> collectionBindings 
            = new HashMap<String, ClassCollectionBinding>();
    
    private Map<String, ClassActionBinding> actionBindings 
            = new HashMap<String, ClassActionBinding>();


    private static String validClassAliasCharacters 
            = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    
    private JvControllerClassBinding() {
    }

    public static JvControllerClassBinding getInstance() {
        return instance;
    }

    public Map<String, ClassBeanBinding> getBeanBindings() {
        return beanBindings;
    }

    public Map<String, ClassCollectionBinding> getCollectionBindings() {
        return collectionBindings;
    }

    public Map<String, ClassPropertyBinding> getPropertyBindings() {
        return propertyBindings;
    }

    public Map<String, ClassActionBinding> getActionBindings() {
        return actionBindings;
    }

    public static String getValidClassAliasCharacters() {
        return validClassAliasCharacters;
    }

    public void addPropertyBinding(ClassPropertyBinding propertyBinding) 
            throws Exception {
        
        propertyBindings.put(propertyBinding.getViewFrom(), propertyBinding);
    }

    public void addBeanBinding(ClassBeanBinding beanBinding) 
            throws Exception {
        
        beanBindings.put(beanBinding.getViewFrom(), beanBinding);
    }

    public void addCollectionBinding(ClassCollectionBinding collectionBinding) 
            throws Exception {
        
        // TODO
        String uuid = "cb" + UUID.randomUUID().toString().replace("-", "");
        collectionBindings.put(uuid, collectionBinding);
    }

    public void addActionBinding(ClassActionBinding actionBinding) 
            throws Exception {
        
        // TODO
        String uuid = "ab" + UUID.randomUUID().toString().replace("-", "");
        actionBindings.put(uuid, actionBinding);
    }

    public List<ClassActionBinding> getActionClassBindings(String classAlias) {
        List<ClassActionBinding> classActionBindings = new ArrayList<ClassActionBinding>();
        for (ClassActionBinding cab : actionBindings.values()) {
            String[] evals = cab.getEval().split("[ \\.(),]");
            for (String eval : evals) {
                if (eval.trim().equals(classAlias)) {
                    classActionBindings.add(cab);
                }
            }
            String[] viewProperties = cab.getViewProperty().split("[ \\.(),]");
            for (String viewProperty : viewProperties) {
                if (viewProperty.trim().equals(classAlias)) {
                    if (!classActionBindings.contains(cab)) {
                        classActionBindings.add(cab);
                    }
                }
            }
        }
        return classActionBindings;
    }
    
    public List<ClassBinding> getClassBindings(String classAlias) {
        List<ClassBinding> classBindings = new ArrayList<ClassBinding>();
        classBindings.addAll(getGenericClassBindings(
                propertyBindings.values(), classAlias));
        
        classBindings.addAll(getGenericClassBindings(
                beanBindings.values(), classAlias));
        
        classBindings.addAll(getGenericClassBindings(
                collectionBindings.values(), classAlias));
        return classBindings;
    }

    private List<ClassBinding> getGenericClassBindings(
            Collection<? extends ClassBinding> collection, String classAlias) {
        
        List<ClassBinding> classBindings = new ArrayList<ClassBinding>();
        for (ClassBinding cpb : collection) {
            String[] viewsFrom = cpb.getViewFrom().split("[ \\.(),]");
            for (String viewFrom : viewsFrom) {
                if (viewFrom.trim().equals(classAlias)) {
                    classBindings.add(cpb);
                }
            }
        
            if (cpb instanceof ClassCollectionBinding) {
                ClassCollectionBinding ccb = (ClassCollectionBinding) cpb;
                
                String[] viewProperties = ccb.getViewInvokeAdd().split("[ \\.(),]");
                for (String viewProperty : viewProperties) {
                    if (viewProperty.trim().equals(classAlias)) {
                        if (!classBindings.contains(ccb)) {
                            classBindings.add(ccb);
                        }
                    }
                }
                
                viewProperties = ccb.getViewInvokeRemove().split("[ \\.(),]");
                for (String viewProperty : viewProperties) {
                    if (viewProperty.trim().equals(classAlias)) {
                        if (!classBindings.contains(ccb)) {
                            classBindings.add(ccb);
                        }
                    }
                }
                
            }
        }
        
        return classBindings;
    }
    
}
