package ono.leo.jvcontroller.core;

import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;
import ono.leo.jvcontroller.bean.binding.ClassActionBinding;
import ono.leo.jvcontroller.bean.binding.ClassBeanBinding;
import ono.leo.jvcontroller.bean.binding.ClassCollectionBinding;
import ono.leo.jvcontroller.bean.binding.ClassPropertyBinding;
import ono.leo.jvcontroller.bean.binding.InstanceActionBinding;
import ono.leo.jvcontroller.bean.binding.InstanceBeanBinding;
import ono.leo.jvcontroller.bean.binding.InstancePropertyBinding;

/**
 * Oficial implementation of JvController interface.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (14/08/2012 11:41)
 */
public class JvController implements JvControllerInterface {
    
    private static JvController instance = new JvController();
    
    private JvControllerClassContext classContext 
            = JvControllerClassContext.getInstance();

    private JvControllerClassBinding classBinding
            = JvControllerClassBinding.getInstance();
    
    private JvControllerInstanceBinding instanceBinding 
            = new JvControllerInstanceBinding();
    
    private BeanInstanceELAccessor elContext 
            = BeanInstanceELAccessor.getInstance();
    
    private JvController() {
    }

    public static JvController getInstance() {
        return instance;
    }

    public BeanInstanceELAccessor getELContext() {
        return elContext;
    }
    
    // --- Class context ---
    
    public void registerViewClass(String classAlias, String className) 
            throws Exception {
        
        classContext.registerViewClass(classAlias, className);
    }

    public void registerModelClass(String classAlias, String className) 
            throws Exception {
        
        classContext.registerModelClass(classAlias, className);
    }

    public void registerControllerClass(String classAlias, String className) 
            throws Exception {

        classContext.registerControllerClass(classAlias, className);
    }

    public void registerConversorClass(String classAlias, String className) 
            throws Exception {
        
        classContext.registerConversorClass(classAlias, className);
    }

    public void registerValidatorClass(String classAlias, String className) 
            throws Exception {

        classContext.registerValidatorClass(classAlias, className);
    }

    // --- Binding between classes ---
    
    public void bindClassProperty(
            String view, 
            String model, 
            String conversor, 
            String validator) throws Exception {
        
        bindClassProperty(view, "", "", model, "", ""
                , conversor, "", "", validator, "", "", "", "");
    }
    public void bindClassProperty(
            String viewFromEval, 
            String viewToEval, 
            String modelFromEval, 
            String modelToEval, 
            String conversor, 
            String validator, 
            String viewVar, 
            String modelVar) throws Exception {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void bindClassProperty(
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
            String modelValueVar) throws Exception {

        
        ClassPropertyBinding pb = new ClassPropertyBinding();
        pb.setViewFrom(view.trim().length() > 0 ? view : viewFrom);
        pb.setViewTo(view.trim().length() > 0 ? view : viewTo);
        pb.setModelTo(model.trim().length() > 0 ? model : modelTo);
        pb.setModelFrom(model.trim().length() > 0 ? model : modelFrom);
        pb.setConversorTo(conversor.trim().length() > 0 ? conversor : conversorTo);
        pb.setConversorFrom(conversor.trim().length() > 0 ? conversor : conversorFrom);
        pb.setValidator(validator);
        pb.setViewValueVar(viewValueVar);
        pb.setModelValueVar(modelValueVar);
        pb.setViewClassAlias(pb.getViewFrom().split("\\.")[0]);
        pb.setModelClassAlias(pb.getModelTo().split("\\.")[0]);
        
        // TODO
        if (pb.getViewClassAlias().trim().length() > 0) {
            if (Character.isLowerCase(pb.getViewClassAlias().charAt(0))) {
                pb.setViewClassAlias("");
            }
        }

        if (pb.getModelClassAlias().trim().length() > 0) {
            if (Character.isLowerCase(pb.getModelClassAlias().charAt(0))) {
                pb.setModelClassAlias("");
            }
        }
        
        classBinding.addPropertyBinding(pb);
    }
    
    public void bindClassProperty(
            String view, 
            String viewFrom, 
            String viewTo, 
            String model, 
            String modelTo, 
            String modelFrom, 
            String conversor, 
            String conversorTo, 
            String conversorFrom, 
            String validator, 
            String viewVar, 
            String modelVar, 
            String viewInstance,
            String modelInstance) throws Exception {
        
        ClassPropertyBinding pb = new ClassPropertyBinding();
        pb.setViewFrom(view.trim().length() > 0 ? view : viewFrom);
        pb.setViewTo(view.trim().length() > 0 ? view : viewTo);
        pb.setModelTo(model.trim().length() > 0 ? model : modelTo);
        pb.setModelFrom(model.trim().length() > 0 ? model : modelFrom);
        pb.setConversorTo(conversor.trim().length() > 0 ? conversor : conversorTo);
        pb.setConversorFrom(conversor.trim().length() > 0 ? conversor : conversorFrom);
        pb.setValidator(validator);
        pb.setViewVar(viewVar);
        pb.setModelVar(modelVar);
        pb.setViewInstance(viewInstance);
        pb.setModelInstance(modelInstance);
        pb.setViewClassAlias(pb.getViewFrom().split("\\.")[0]);
        pb.setModelClassAlias(pb.getModelTo().split("\\.")[0]);
        
        // TODO
        if (pb.getViewClassAlias().trim().length() > 0) {
            if (Character.isLowerCase(pb.getViewClassAlias().charAt(0))) {
                pb.setViewClassAlias("");
            }
        }

        if (pb.getModelClassAlias().trim().length() > 0) {
            if (Character.isLowerCase(pb.getModelClassAlias().charAt(0))) {
                pb.setModelClassAlias("");
            }
        }
        classBinding.addPropertyBinding(pb);
    }

    public void bindClassBean(String view, String model) throws Exception {
        ClassBeanBinding cbb = new ClassBeanBinding();
        cbb.setViewFrom(view);
        cbb.setViewTo(view);
        cbb.setModelFrom(model);
        cbb.setModelTo(model);
        cbb.setViewClassAlias(cbb.getViewFrom().split("\\.")[0]);
        cbb.setModelClassAlias(cbb.getModelTo().split("\\.")[0]);
        
        classBinding.addBeanBinding(cbb);
    }
    
    public void bindClassBean(
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
            String instance) {
            
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void bindClassCollection(
            String view, 
            String model,
            String viewInvokeAdd,
            String viewInvokeRemove,
            String viewItemType) throws Exception {
        
        ClassCollectionBinding ccb = new ClassCollectionBinding();
        ccb.setViewFrom(view);
        ccb.setViewTo(view);
        ccb.setModelFrom(model);
        ccb.setModelTo(model);
        ccb.setViewInvokeAdd(viewInvokeAdd);
        ccb.setViewInvokeRemove(viewInvokeRemove);
        ccb.setViewClassAlias(ccb.getViewFrom().split("\\.")[0]);
        if (ccb.getViewClassAlias().trim().length() == 0) {
            ccb.setViewClassAlias(ccb.getViewInvokeAdd().split("\\.")[0]);
        }
        if (ccb.getViewClassAlias().trim().length() == 0) {
            ccb.setViewClassAlias(ccb.getViewInvokeRemove().split("\\.")[0]);
        }
        ccb.setModelClassAlias(ccb.getModelTo().split("\\.")[0]);
        ccb.setViewItemType(viewItemType);
        
        classBinding.addCollectionBinding(ccb);        
    }

    public void bindClassCollection(
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
            String viewRemoveMethod) throws Exception {

        throw new UnsupportedOperationException("Not supported yet.");
    }
    


    // --- Binding between instances ---

    public void newInstance(
            String beanId, 
            String classAlias) throws Exception {
        
        Class beanClass = classContext.getModelClasses().get(classAlias);
        if (beanClass == null) {
            beanClass = classContext.getViewClasses().get(classAlias);
        }
        if (beanClass == null) {
            beanClass = classContext.getControllerClasses().get(classAlias);
        }
        if (beanClass == null) {
            beanClass = classContext.getConversorClasses().get(classAlias);
        }
        if (beanClass == null) {
            beanClass = classContext.getValidatorClasses().get(classAlias);
        }
        if (beanClass == null) {
            throw new Exception(
                    "Class alias \"" + classAlias + "\" not found !");
        }
        Object beanObj = beanClass.newInstance();
        elContext.addBean(beanId, beanObj);
    }
    
    public void bindInstanceProperty(
            String view, 
            String model, 
            String conversor, 
            String validator) throws Exception {
        
        InstancePropertyBinding ipb = new InstancePropertyBinding();
        ipb.setViewFrom(view);
        ipb.setViewTo(view);
        ipb.setModelTo(model);
        ipb.setModelFrom(model);
        ipb.setConversorTo(conversor);
        ipb.setConversorFrom(conversor);
        ipb.setValidator(validator);
        instanceBinding.addPropertyBinding(ipb);
    }
    
    public void bindInstanceProperty(
            String viewFromEval, 
            String viewToEval, 
            String modelToEval, 
            String modelFromEval, 
            String conversor, 
            String validator,
            String viewValueVar,
            String modelValueVar) throws Exception {
        
        InstancePropertyBinding ipb = new InstancePropertyBinding();
        ipb.setViewFromEval(viewFromEval);
        ipb.setViewToEval(viewToEval);
        ipb.setModelToEval(modelToEval);
        ipb.setModelFromEval(modelFromEval);
        ipb.setConversorTo(conversor);
        ipb.setConversorFrom(conversor);
        ipb.setValidator(validator);
        ipb.setViewValueVar(viewValueVar);
        ipb.setModelValueVar(modelValueVar);
        instanceBinding.addPropertyBinding(ipb);
    }
    
    public void bindInstanceProperty(
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
            String modelValueVar) throws Exception {
        
        InstancePropertyBinding ipb = new InstancePropertyBinding();
        ipb.setViewFrom(view.trim().length() > 0 ? view : viewFrom);
        ipb.setViewTo(view.trim().length() > 0 ? view : viewTo);
        ipb.setViewFromEval(viewFromEval);
        ipb.setViewToEval(viewToEval);
        ipb.setModelFrom(model.trim().length() > 0 ? model : modelFrom);
        ipb.setModelTo(model.trim().length() > 0 ? model : modelTo);
        ipb.setModelToEval(modelToEval);
        ipb.setModelFromEval(modelFromEval);
        ipb.setConversorTo(conversor.trim().length() > 0 ? conversor : conversorTo);
        ipb.setConversorFrom(conversor.trim().length() > 0 ? conversor : conversorTo);
        ipb.setValidator(validator);
        ipb.setViewValueVar(viewValueVar);
        ipb.setModelValueVar(modelValueVar);
        instanceBinding.addPropertyBinding(ipb);
    }
    
    public void bindInstanceBean(
            String view, 
            String model) throws Exception {
        
        InstanceBeanBinding ibb = new InstanceBeanBinding();
        ibb.setViewFrom(view);
        ibb.setViewTo(view);
        ibb.setModelFrom(model);
        ibb.setModelTo(model);
        instanceBinding.addBeanBinding(ibb);
    }
    
    public void bindInstanceBean(
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
            String instance) throws Exception {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void bindInstanceCollection(
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
            String viewRemoveMethod) throws Exception {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    // --- Action ---

    public void bindClassAction(
            String viewProperty, 
            String eval, 
            String evalRet, 
            String updateView, 
            String updateModel, 
            String label) throws Exception {
        
        ClassActionBinding cab = new ClassActionBinding();
        cab.setViewProperty(viewProperty);
        cab.setEval(eval);
        cab.setEvalRet(evalRet);
        cab.setUpdateModel(updateModel);
        cab.setUpdateView(updateView);
        cab.setLabel(label);
        classBinding.addActionBinding(cab);
    }

    public void bindInstanceAction(
            String viewProperty, 
            String eval, 
            String evalRet, 
            String updateView, 
            String updateModel, 
            String label) throws Exception {
        
        InstanceActionBinding iab = new InstanceActionBinding();
        iab.setViewProperty(viewProperty);
        iab.setEval(eval);
        iab.setEvalRet(evalRet);
        iab.setUpdateModel(updateModel);
        iab.setUpdateView(updateView);
        iab.setLabel(label);
        iab.createAndBindAction();
    }
    
    // --- Update view, update model ---

    public void updateView(String... beanIds) throws Exception {
        instanceBinding.updateView(beanIds);
    }

    public void updateModel(String... beanIds) throws Exception {
        instanceBinding.updateModel(beanIds);
    }

}
