package ono.leo.jvcontroller.bean.binding;

import ono.leo.jvcontroller.conversor.Conversor;
import ono.leo.jvcontroller.validator.Validator;

/**
 * InstancePropertyBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (16/08/2012 09:42)
 */
public class InstancePropertyBinding extends InstanceBinding {

    @Override
    public void updateView(String ... beanIds) throws Exception {

        // Verifica se este vinculo foi solicitado para ser atualizado
        String bindingId = "";
        if (beanIds.length > 0) {
            bindingId = beanIds[0].trim();
        }
        if (bindingId.trim().length() > 0) {
            if (beanIds[0].contains(".")) {
                throw new Exception("Update model eval expression invalid !");
            }
            else {
                if (!id.equals(beanIds[0])) return;
            }
        }        
        
        if (modelFrom.trim().length() == 0 
                && modelFromEval.trim().length() == 0) {
            return;
        }

        // Get model value
        Object modelValue = elContext.get(modelFrom);
        if (modelValue == null) {
            modelValue = elContext.evaluate(modelFromEval);
        }
        
        // Conversor
        Conversor conversor = (Conversor) elContext.get(conversorFrom);
        if (conversor != null) {
            modelValue = conversor.getAsView(modelValue);
        }

        // Create modelValueVar
        if (modelValueVar.trim().length() > 0) {
            elContext.removeBeanById(modelValueVar);
            elContext.addBean(modelValueVar, modelValue);
        }
        
        // update view
        if (viewTo.trim().length() > 0) {
            elContext.set(viewTo, modelValue);
        }
        if (viewToEval.trim().length() > 0) {
            elContext.evaluate(viewToEval);
        }
    }

    @Override
    public void updateModel(String ... beanIds) throws Exception {
        
        // Verifica se este vinculo foi solicitado para ser atualizado
        String bindingId = "";
        if (beanIds.length > 0) {
            bindingId = beanIds[0].trim();
        }
        if (bindingId.trim().length() > 0) {
            if (beanIds[0].contains(".")) {
                throw new Exception("Update model eval expression invalid !");
            }
            else {
                if (!id.equals(beanIds[0])) return;
            }
        }        
        
        if (viewFrom.trim().length() == 0 
                && viewFromEval.trim().length() == 0) {
            return;
        }
        
        // Get view value
        Object viewValue = elContext.get(viewFrom);
        if (viewValue == null) {
            viewValue = elContext.evaluate(viewFromEval);
        }
        
        // Conversor
        Conversor conversor = (Conversor) elContext.get(conversorTo);
        if (conversor != null) {
            viewValue = conversor.getAsModel(viewValue);
        }
        
        // Validator
        Validator validatorObj = (Validator) elContext.get(validator);
        if (validatorObj != null) {
            validatorObj.validate(viewValue);
        }

        // Create viewValueVar
        if (viewValueVar.trim().length() > 0) {
            elContext.removeBeanById(viewValueVar);
            elContext.addBean(viewValueVar, viewValue);
        }
        
        // TODO must update all values after
        // converting and validation all binded properties
        if (modelTo.trim().length() > 0) {
            elContext.set(modelTo, viewValue);
        }
        if (modelToEval.trim().length() > 0) {
            elContext.evaluate(modelToEval);
        }
    }

    @Override
    public Object getAssociatedModelInstance(Object viewInstance) throws Exception {
        return null;
    }

    @Override
    public Object getAssociatedViewInstance(Object modelInstance) throws Exception {
        return null;
    }

    @Override
    public void removeAllChildrens() throws Exception {
        elContext.getBeans().values().remove(getModelInstance());
        elContext.getBeans().values().remove(getViewInstance());
    }

}
