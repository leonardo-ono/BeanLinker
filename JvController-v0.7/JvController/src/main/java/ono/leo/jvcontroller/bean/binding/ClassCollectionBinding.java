package ono.leo.jvcontroller.bean.binding;

/**
 * CollectionBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 16:03)
 */
public class ClassCollectionBinding extends ClassBinding {
    
    private String viewItemType;
    private String viewInvokeAdd;
    private String viewInvokeRemove;

    public String getViewItemType() {
        return viewItemType;
    }

    public void setViewItemType(String viewItemType) {
        this.viewItemType = viewItemType;
    }

    public String getViewInvokeAdd() {
        return viewInvokeAdd;
    }

    public void setViewInvokeAdd(String viewInvokeAdd) {
        this.viewInvokeAdd = viewInvokeAdd;
    }

    public String getViewInvokeRemove() {
        return viewInvokeRemove;
    }

    public void setViewInvokeRemove(String viewInvokeRemove) {
        this.viewInvokeRemove = viewInvokeRemove;
    }

    @Override
    public String toString() {
        return super.toString() + "/CollectionBinding{" + "viewAddMethod=" 
            + viewInvokeAdd + ", viewRemoveMethod=" + viewInvokeRemove + '}';
    }

    @Override
    public InstanceBinding createInstanceBinding(InstanceBeanBinding beanBinding) {
        String viewFromEval = ""; // TODO ?
        String viewToEval = ""; // TODO ?
        String modelToEval = ""; // TODO ?
        String modelFromEval = ""; // TODO ?
        String viewValueVar = ""; // TODO ?
        String modelValueVar = ""; // TODO ?
        
        InstanceCollectionBinding icb = new InstanceCollectionBinding();
        icb.setViewFrom(replaceClassToInstance(viewFrom, beanBinding.viewFrom, viewClassAlias));
        icb.setViewTo(replaceClassToInstance(viewTo, beanBinding.viewTo, viewClassAlias));
        icb.setViewFromEval(viewFromEval);
        icb.setViewToEval(viewToEval);
        icb.setModelFrom(replaceClassToInstance(modelFrom, beanBinding.modelFrom, modelClassAlias));
        icb.setModelTo(replaceClassToInstance(modelTo, beanBinding.modelTo, modelClassAlias));
        icb.setModelToEval(modelToEval);
        icb.setModelFromEval(modelFromEval);
        icb.setConversorTo(conversorTo);
        icb.setConversorFrom(conversorFrom);
        icb.setValidator(validator);
        icb.setViewValueVar(viewValueVar);
        icb.setModelValueVar(modelValueVar);
        icb.setViewInvokeAdd(replaceClassToInstance(viewInvokeAdd, beanBinding.viewFrom, viewClassAlias));
        icb.setViewInvokeRemove(replaceClassToInstance(viewInvokeRemove, beanBinding.viewFrom, viewClassAlias));
        icb.setViewItemType(viewItemType);
        return icb;
    }
    
}
