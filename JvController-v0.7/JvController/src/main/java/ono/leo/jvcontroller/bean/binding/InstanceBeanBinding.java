package ono.leo.jvcontroller.bean.binding;

import java.util.ArrayList;
import java.util.List;

/**
 * InstanceBeanBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 09:46)
 */
public class InstanceBeanBinding extends InstanceBinding {

    private List<InstanceBinding> bindings = new ArrayList<InstanceBinding>();
    
    public void addBinding(InstanceBinding binding) {
        bindings.add(binding);
    }
    
    @Override
    public void updateView() throws Exception {
        for (InstanceBinding binding : bindings) {
            binding.updateView();
        }
    }

    @Override
    public void updateModel() throws Exception {
        for (InstanceBinding binding : bindings) {
            binding.updateModel();
        }
    }
        
}
