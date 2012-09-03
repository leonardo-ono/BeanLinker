package ono.leo.jvcontroller.bean.binding;

import java.util.ArrayList;
import java.util.List;
import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;

/**
 * InstanceBeanBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 09:46)
 */
public class InstanceBeanBinding extends InstanceBinding {

    private BeanInstanceELAccessor elContext
            = BeanInstanceELAccessor.getInstance();
    
    private List<InstanceBinding> bindings = new ArrayList<InstanceBinding>();
    
    public void addBinding(InstanceBinding binding) {
        bindings.add(binding);
    }
    
    @Override
    public void updateView(String ... beanIds) throws Exception {
        String bindingId = "";
        if (beanIds.length > 0) {
            bindingId = beanIds[0].trim();
        }
        if (bindingId.trim().length() > 0) {
            bindingId = bindingId.split("\\.")[0];
            if (beanIds[0].contains(".")) {
                beanIds[0] = beanIds[0].replace(bindingId + ".", "");
            }
            else {
                beanIds[0] = beanIds[0].replace(bindingId, "");
            }
        }        
        for (InstanceBinding binding : bindings) {
            if (binding.getId().equals(bindingId) || bindingId.length()==0) {
                binding.updateView(beanIds);
            }
        }
    }

    @Override
    public void updateModel(String ... beanIds) throws Exception {
        String bindingId = "";
        if (beanIds.length > 0) {
            bindingId = beanIds[0].trim();
        }
        if (bindingId.trim().length() > 0) {
            bindingId = bindingId.split("\\.")[0];
            if (beanIds[0].contains(".")) {
                beanIds[0] = beanIds[0].replace(bindingId + ".", "");
            }
            else {
                beanIds[0] = beanIds[0].replace(bindingId, "");
            }
        }        
        for (InstanceBinding binding : bindings) {
            if (binding.getId().equals(bindingId) || bindingId.length()==0) {
                binding.updateModel(beanIds);
            }
        }
    }

    // TODO ?
    @Override
    public Object getAssociatedModelInstance(Object viewInstance) throws Exception {
        Object viewObj = elContext.get(viewFrom.split("\\.")[0]);
        if (viewInstance.equals(viewObj)) {
            Object modelObj = elContext.get(modelTo.split("\\.")[0]);
            return modelObj;
        }
        Object ret = null;
        for (InstanceBinding binding : bindings) {
            ret = binding.getAssociatedModelInstance(viewInstance);
            if (ret != null) {
                return ret;
            }
        }
        return ret;
    }

    // TODO ?
    @Override
    public Object getAssociatedViewInstance(Object modelInstance) throws Exception {
        Object modelObj = elContext.get(modelTo.split("\\.")[0]);
        if (modelInstance.equals(modelObj)) {
            Object viewObj = elContext.get(viewFrom.split("\\.")[0]);
            return viewObj;
        }
        Object ret = null;
        for (InstanceBinding binding : bindings) {
            ret = binding.getAssociatedViewInstance(modelInstance);
            if (ret != null) {
                return ret;
            }
        }
        return ret;
    }

    @Override
    public void removeAllChildrens() throws Exception {
        elContext.getBeans().values().remove(getModelInstance());
        elContext.getBeans().values().remove(getViewInstance());
        for (InstanceBinding ibb : bindings) {
            ibb.removeAllChildrens();
        }
    }

}
