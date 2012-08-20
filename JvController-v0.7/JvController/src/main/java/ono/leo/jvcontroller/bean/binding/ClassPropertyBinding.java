package ono.leo.jvcontroller.bean.binding;

/**
 * PropertyBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 15:36)
 */
public class ClassPropertyBinding extends ClassBinding {

    @Override
    public InstanceBinding createInstanceBinding(InstanceBeanBinding beanBinding) {
        String viewFromEval = ""; // TODO
        String viewToEval = ""; // TODO
        String modelToEval = ""; // TODO
        String modelFromEval = ""; // TODO
        
        InstancePropertyBinding ipb = new InstancePropertyBinding();
        ipb.setViewFrom(replaceClassToInstance(viewFrom, beanBinding.viewFrom, viewClassAlias));
        ipb.setViewTo(replaceClassToInstance(viewTo, beanBinding.viewTo, viewClassAlias));
        ipb.setViewFromEval(viewFromEval);
        ipb.setViewToEval(viewToEval);
        ipb.setModelFrom(replaceClassToInstance(modelFrom, beanBinding.modelFrom, modelClassAlias));
        ipb.setModelTo(replaceClassToInstance(modelTo, beanBinding.modelTo, modelClassAlias));
        ipb.setModelToEval(modelToEval);
        ipb.setModelFromEval(modelFromEval);
        ipb.setConversorTo(conversorTo);
        ipb.setConversorFrom(conversorFrom);
        ipb.setValidator(validator);
        ipb.setViewValueVar(viewValueVar);
        ipb.setModelValueVar(modelValueVar);
        
        //System.out.println("creating instance binding ---> " + ipb);
        
        return ipb;
    }
    
}
