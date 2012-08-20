package ono.leo.jvcontroller.bean.binding;

/**
 * BeanBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 16:02)
 */
public class ClassBeanBinding extends ClassBinding {

    @Override
    public InstanceBinding createInstanceBinding(InstanceBeanBinding beanBinding) {
        String viewFromEval = ""; // TODO
        String viewToEval = ""; // TODO
        String modelToEval = ""; // TODO
        String modelFromEval = ""; // TODO
        String viewValueVar = ""; // TODO
        String modelValueVar = ""; // TODO
        
        InstanceBeanBinding ibb = new InstanceBeanBinding();
        ibb.setViewFrom(replaceClassToInstance(viewFrom, beanBinding.viewFrom, viewClassAlias));
        ibb.setViewTo(replaceClassToInstance(viewTo, beanBinding.viewTo, viewClassAlias));
        ibb.setViewFromEval(viewFromEval);
        ibb.setViewToEval(viewToEval);
        ibb.setModelFrom(replaceClassToInstance(modelFrom, beanBinding.modelFrom, modelClassAlias));
        ibb.setModelTo(replaceClassToInstance(modelTo, beanBinding.modelTo, modelClassAlias));
        ibb.setModelToEval(modelToEval);
        ibb.setModelFromEval(modelFromEval);
        ibb.setConversorTo(conversorTo);
        ibb.setConversorFrom(conversorFrom);
        ibb.setValidator(validator);
        ibb.setViewValueVar(viewValueVar);
        ibb.setModelValueVar(modelValueVar);
        
        return ibb;
    }
    
}
