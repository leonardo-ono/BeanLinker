package ono.leo.jvcontroller.bean.binding;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import ono.leo.jvcontroller.bean.access.BeanInstanceELAccessor;
import ono.leo.jvcontroller.core.JvController;

/**
 * InstanceActionBinding.
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
 * @since 1.0 (18/08/2012 19:20)
 */
public class ClassActionBinding {

    private String viewClassAlias = "";
    private String modelClassAlias = "";

    private String viewProperty = "";
    private String eval = "";
    private String evalRet = "";
    private String updateView = "";
    private String updateModel = "";
    private String label = "";
                    
    private static JvController jc = JvController.getInstance();
    private static BeanInstanceELAccessor elContext = jc.getELContext();

    public String getModelClassAlias() {
        return modelClassAlias;
    }

    public void setModelClassAlias(String modelClassAlias) {
        this.modelClassAlias = modelClassAlias;
    }

    public String getViewClassAlias() {
        return viewClassAlias;
    }

    public void setViewClassAlias(String viewClassAlias) {
        this.viewClassAlias = viewClassAlias;
    }

    public String getEval() {
        return eval;
    }

    public void setEval(String eval) {
        this.eval = eval;
    }

    public String getEvalRet() {
        return evalRet;
    }

    public void setEvalRet(String evalRet) {
        this.evalRet = evalRet;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUpdateModel() {
        return updateModel;
    }

    public void setUpdateModel(String updateModel) {
        this.updateModel = updateModel;
    }

    public String getUpdateView() {
        return updateView;
    }

    public void setUpdateView(String updateView) {
        this.updateView = updateView;
    }

    public String getViewProperty() {
        return viewProperty;
    }

    public void setViewProperty(String viewProperty) {
        this.viewProperty = viewProperty;
    }

    public InstanceActionBinding createInstanceActionBinding(
            String viewInstanceValue, String modelInstanceValue) {
        
        InstanceActionBinding iab = new InstanceActionBinding();
        iab.setViewProperty(replaceClassToInstance(viewProperty, viewInstanceValue, viewClassAlias));
        String evalRepl = replaceClassToInstance(eval, viewInstanceValue, viewClassAlias);
        evalRepl = replaceClassToInstance(evalRepl, modelInstanceValue, modelClassAlias);
        iab.setEval(evalRepl);
        iab.setEvalRet(evalRet);

        evalRepl = replaceClassToInstance(updateModel, modelInstanceValue, modelClassAlias);
        evalRepl = replaceClassToInstance(evalRepl, viewInstanceValue, viewClassAlias);
        iab.setUpdateModel(evalRepl);

        evalRepl = replaceClassToInstance(updateView, modelInstanceValue, modelClassAlias);
        evalRepl = replaceClassToInstance(evalRepl, viewInstanceValue, viewClassAlias);
        iab.setUpdateView(evalRepl);
        
        iab.setLabel(label);
        return iab;
    }
    
    public String replaceClassToInstance(String classValue, String instanceValue, String classAlias) {
        String value = "";
        String valueComplete = "";
        for (int i=0; i<classValue.length(); i++) {
            char c = classValue.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                value += c;
            }
            if (!Character.isLetterOrDigit(c) || i==(classValue.length()-1)) {
                if (value.equals(classAlias)) {
                    valueComplete += instanceValue + c;
                }
                else {
                    String cc = (classValue.length()-1 == i && Character.isLetterOrDigit(c) ? "" : c + "");
                    valueComplete += value + cc;
                }
                value = "";
            }
        }
        return valueComplete;
    }
    
}
