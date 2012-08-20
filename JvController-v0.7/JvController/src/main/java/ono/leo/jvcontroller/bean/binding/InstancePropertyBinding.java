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
    public void updateView() throws Exception {
        if (modelFrom.trim().length() == 0 
                && modelFromEval.trim().length() == 0) {
            return;
        }

        // Get model value
        Object modelValue = biela.get(modelFrom);
        if (modelValue == null) {
            modelValue = biela.evaluate(modelFromEval);
        }
        
        // Conversor
        Conversor conversor = (Conversor) biela.get(conversorFrom);
        if (conversor != null) {
            modelValue = conversor.getAsView(modelValue);
        }

        // Create modelValueVar
        if (modelValueVar.trim().length() > 0) {
            biela.removeBean(modelValueVar);
            biela.addBean(modelValueVar, modelValue);
        }
        
        // update view
        if (viewTo.trim().length() > 0) {
            biela.set(viewTo, modelValue);
        }
        if (viewToEval.trim().length() > 0) {
            biela.evaluate(viewToEval);
        }
    }

    @Override
    public void updateModel() throws Exception {
        if (viewFrom.trim().length() == 0 
                && viewFromEval.trim().length() == 0) {
            return;
        }
        
        // Get view value
        Object viewValue = biela.get(viewFrom);
        if (viewValue == null) {
            viewValue = biela.evaluate(viewFromEval);
        }
        
        // Conversor
        Conversor conversor = (Conversor) biela.get(conversorTo);
        if (conversor != null) {
            viewValue = conversor.getAsModel(viewValue);
        }
        
        // Validator
        Validator validatorObj = (Validator) biela.get(validator);
        if (validatorObj != null) {
            validatorObj.validate(viewValue);
        }

        // Create viewValueVar
        if (viewValueVar.trim().length() > 0) {
            biela.removeBean(viewValueVar);
            biela.addBean(viewValueVar, viewValue);
        }
        
        // TODO must update all values after
        // converting and validation all binded properties
        if (modelTo.trim().length() > 0) {
            biela.set(modelTo, viewValue);
        }
        if (modelToEval.trim().length() > 0) {
            biela.evaluate(modelToEval);
        }
    }
    
}
