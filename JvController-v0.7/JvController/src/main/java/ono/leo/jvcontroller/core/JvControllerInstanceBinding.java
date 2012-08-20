package ono.leo.jvcontroller.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;
import ono.leo.jvcontroller.bean.binding.ClassActionBinding;
import ono.leo.jvcontroller.bean.binding.ClassBinding;
import ono.leo.jvcontroller.bean.binding.InstanceActionBinding;
import ono.leo.jvcontroller.bean.binding.InstanceBeanBinding;
import ono.leo.jvcontroller.bean.binding.InstanceBinding;
import ono.leo.jvcontroller.bean.binding.InstanceCollectionBinding;
import ono.leo.jvcontroller.bean.binding.InstancePropertyBinding;

/**
 * JvControllerInstanceBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 15:52)
 */
public class JvControllerInstanceBinding {
    
    private Map<String, InstanceBinding> bindings 
            = new HashMap<String, InstanceBinding>();

    private static BeanInstanceELAccessor elContext
            = BeanInstanceELAccessor.getInstance();

    private static JvControllerClassContext classContext
            = JvControllerClassContext.getInstance();
    
    private static JvControllerClassBinding classBinding
            = JvControllerClassBinding.getInstance();
    
    private static JvControllerInstanceBinding instance =
            new JvControllerInstanceBinding();
    
    public JvControllerInstanceBinding() {
    }

    public Map<String, InstanceBinding> getBindings() {
        return bindings;
    }

    public void addPropertyBinding(InstancePropertyBinding propertyBinding) 
            throws Exception {
        
        bindings.put(propertyBinding.getViewFrom(), propertyBinding);
    }

    public void addBeanBinding(InstanceBeanBinding beanBinding)  throws Exception {
        addBeanBinding(beanBinding, true);
    }
    
    private void addBeanBinding(InstanceBeanBinding beanBinding, boolean first) 
            throws Exception {
        
        // Bind all children bindings
        Object viewObj = elContext.get(beanBinding.getViewFrom());
        Object modelObj = elContext.get(beanBinding.getModelTo());
        
        String viewClassName = viewObj.getClass().getName();
        String viewClassAlias = classContext.getClassNames().get(viewClassName);
        if (viewClassAlias == null) {
            throw new Exception("Class  \"" + viewClassName + "\" not registered !");
        }
        String modeClassName = "";
        String modelClassAlias = "";
        
        if (modelObj != null) {
            modeClassName = modelObj.getClass().getName();
            modelClassAlias = classContext.getClassNames().get(modeClassName);
        }
        
        //System.out.println("view class: " + className);
        //System.out.println("view alias: " + classAlias);
        //System.out.println(beanBinding);
        List<ClassBinding> classBindings 
                = classBinding.getClassBindings(viewClassAlias);
        for (ClassBinding cb : classBindings) {
            // System.out.println("Class binding: " + cb);
            InstanceBinding ibb = cb.createInstanceBinding(beanBinding);
            if (ibb instanceof InstancePropertyBinding) {
                beanBinding.addBinding((InstancePropertyBinding) ibb);
            }
            if (ibb instanceof InstanceBeanBinding) {
                beanBinding.addBinding((InstanceBeanBinding) ibb);
                addBeanBinding((InstanceBeanBinding) ibb, false);
            }
            if (ibb instanceof InstanceCollectionBinding) {
                beanBinding.addBinding((InstanceCollectionBinding) ibb);
            }
        }
        if (first) {
            bindings.put(beanBinding.getViewFrom(), beanBinding);
        }
        
        // Create action's if necessary TODO ?
        List<ClassActionBinding> classActionBindings = classBinding.getActionClassBindings(viewClassAlias);
        for (ClassActionBinding classActionBinding : classActionBindings) {
            classActionBinding.setViewClassAlias(viewClassAlias);
            classActionBinding.setModelClassAlias(modelClassAlias); 
            InstanceActionBinding iab = classActionBinding.createInstanceActionBinding(beanBinding.getViewFrom(), beanBinding.getModelTo());
            iab.createAndBindAction();
        }
                
    }

    public void addCollectionBinding(
            InstanceCollectionBinding collectionBinding) throws Exception {
        
        bindings.put(collectionBinding.getViewFrom(), collectionBinding);
    }
    
    public void updateView(String ... beanIds) throws Exception {
        for (InstanceBinding binding : bindings.values()) {
            binding.updateView();
        }
    }
    
    public void updateModel(String ... beanIds) throws Exception {
        for (InstanceBinding binding : bindings.values()) {
            binding.updateModel();
        }
    }
    
}
