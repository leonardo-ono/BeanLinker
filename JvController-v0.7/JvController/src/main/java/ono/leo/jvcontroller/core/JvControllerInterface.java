package ono.leo.jvcontroller.core;

import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;

/**
 * JvController interface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 11:41)
 */
public interface JvControllerInterface {

    public BeanInstanceELAccessor getELContext();
    
    // --- Class context ---
    
    public void registerViewClass(String classAlias, String className) 
            throws Exception;
    
    public void registerModelClass(String classAlias, String className) 
            throws Exception;
    
    public void registerControllerClass(String classAlias, String className) 
            throws Exception;
    
    public void registerConversorClass(String classAlias, String className) 
            throws Exception;
    
    public void registerValidatorClass(String classAlias, String className) 
            throws Exception;
    
    // --- Binding between classes ---
    
    public void bindClassProperty(
            String bindingId, 
            String view, 
            String model, 
            String conversor, 
            String validator) throws Exception;

    public void bindClassProperty(
            String bindingId, 
            String viewFromEval, 
            String viewToEval, 
            String modelFromEval, 
            String modelToEval, 
            String conversor, 
            String validator,
            String viewVar, 
            String modelVar) throws Exception;

    public void bindClassProperty(
            String bindingId, 
            String view, 
            String viewFrom, 
            String viewTo, 
            String viewFromEval, 
            String viewToEval, 
            String model, 
            String modelTo, 
            String modelFrom, 
            String modelToEval, 
            String modelFromEval, 
            String conversor, 
            String conversorTo, 
            String conversorFrom, 
            String validator,
            String viewValueVar,
            String modelValueVar) throws Exception;
    
    public void bindClassBean(
            String bindingId, 
            String view, 
            String model) throws Exception;
    
    public void bindClassBean(
            String bindingId, 
            String view, 
            String viewFrom, 
            String viewTo, 
            String model, 
            String modelTo, 
            String modelFrom, 
            String conversor, 
            String toConversor, 
            String fromConversor, 
            String validator, 
            String modelVar, 
            String viewVar, 
            String instance) throws Exception;
            
    public void bindClassCollection(
            String bindingIdEval, 
            String itemBindingIdEval, 
            String view, 
            String model,
            String viewInvokeAdd,
            String viewInvokeRemove,
            String viewItemType) throws Exception;

    public void bindClassCollection(
            String bindingId, 
            String view, 
            String viewFrom, 
            String viewTo, 
            String model, 
            String modelTo, 
            String modelFrom, 
            String conversor, 
            String toConversor, 
            String fromConversor, 
            String validator, 
            String modelVar, 
            String viewVar, 
            String instance,
            String viewAddMethod, 
            String viewRemoveMethod) throws Exception;

    // --- Binding between instances ---

    public void newInstance(String beanId, String classAlias) throws Exception;
    
    public void bindInstanceProperty(
            String bindingId, 
            String view, 
            String model, 
            String conversor, 
            String validator) throws Exception;

    public void bindInstanceProperty(
            String bindingId, 
            String viewFromEval, 
            String viewToEval, 
            String modelToEval, 
            String modelFromEval, 
            String conversor, 
            String validator,
            String viewValueVar,
            String modelValueVar) throws Exception;
    
    public void bindInstanceProperty(
            String bindingId, 
            String view, 
            String viewFrom, 
            String viewTo, 
            String viewFromEval, 
            String viewToEval, 
            String model, 
            String modelTo, 
            String modelFrom, 
            String modelToEval, 
            String modelFromEval, 
            String conversor, 
            String conversorTo, 
            String conversorFrom, 
            String validator,
            String viewValueVar,
            String modelValueVar) throws Exception;
    
    public void bindInstanceBean(
            String bindingId, 
            String view, 
            String model) throws Exception;

    public void bindInstanceBean(
            String bindingId, 
            String view, 
            String viewFrom, 
            String viewTo, 
            String model, 
            String modelTo, 
            String modelFrom, 
            String conversor, 
            String toConversor, 
            String fromConversor, 
            String validator, 
            String modelVar, 
            String viewVar, 
            String instance) throws Exception;
            
    public void bindInstanceCollection(
            String bindingId, 
            String view, 
            String viewFrom, 
            String viewTo, 
            String model, 
            String modelTo, 
            String modelFrom, 
            String conversor, 
            String toConversor, 
            String fromConversor, 
            String validator, 
            String modelVar, 
            String viewVar, 
            String instance,
            String viewAddMethod, 
            String viewRemoveMethod) throws Exception;

    // --- Action ---
    
    public void bindClassAction(
            String viewProperty, 
            String eval, 
            String evalRet, 
            String updateView, 
            String updateModel, 
            String label) throws Exception;
    
    public void bindInstanceAction(
            String viewProperty, 
            String eval, 
            String evalRet, 
            String updateView, 
            String updateModel, 
            String label) throws Exception;
    
    // --- Update view, update model ---
    
    public void updateView(String ... beanIds) throws Exception;
    public void updateModel(String ... beanIds) throws Exception;    

    // --- Utils ---
    
    public Object getAssociatedModelInstance(Object viewInstance) 
            throws Exception;
    
    public Object getAssociatedViewInstance(Object modelInstance) 
            throws Exception;
    
    
}
