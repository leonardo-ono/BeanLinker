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
public class InstanceActionBinding {

    private String viewProperty = "";
    private String eval = "";
    private String evalRet = "";
    private String updateView = "";
    private String updateModel = "";
    private String label = "";

    private static JvController jc = JvController.getInstance();
    private static BeanInstanceELAccessor elContext = jc.getELContext();

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

    public void createAndBindAction() throws Exception {
        MeuAction meuAction = new MeuAction();
        elContext.set(viewProperty, meuAction);
    }
    
    private class MeuAction extends AbstractAction {

        public MeuAction() {
            super(label);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jc.updateModel(updateModel);
                elContext.assign(evalRet, eval);
                jc.updateView(updateView);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        
    }
    
}
