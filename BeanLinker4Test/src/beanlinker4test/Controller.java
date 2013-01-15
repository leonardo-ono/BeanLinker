package beanlinker4test;

import br.beanlinker.core.BeanLinker;
import br.beanlinker.core.BeanLinkerImpl;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

/**
 *
 * @author leonardo
 */
public class Controller {

    private static BeanLinker linker = new BeanLinkerImpl();

    public static void main(String[] args) throws Exception {
        linker.registerClass("Model", Model.class.getName());
        linker.registerClass("View", View.class.getName());
        linker.registerClass("Controller", Controller.class.getName());
        
        linker.registerClass("IntegerConversor", IntegerConversor.class.getName());
        linker.registerClass("MyValidator", MyValidator.class.getName());
        
        linker.createAllNewInstances();

        linker.linkProperty("Model.valor", "View.slider.value", "", "", "", "");
        linker.linkProperty("Model.valor", "View.text.text", "integerConversor", "myValidator"
                , "controller.showMessage(exception.message)"
                , "controller.showMessage(exception.message)");

        linker.assign("myAction", new MyAction(".*slider.value"));
        linker.eval("view.sliderChangeValueAction = myAction");

        linker.assign("myAction2", new MyAction(".*text.text"));
        linker.eval("view.text.setAction(myAction2)");

        updateView();
        
        linker.eval("view.setVisible(true)");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    public static class MyAction extends AbstractAction {

        private String regex;

        public MyAction(String regex) {
            this.regex = regex;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                linker.update("view", "model", regex);
            } 
            catch (Exception ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } 
            finally {
                updateView();
            }
        }
    }
    
    public static void updateView() {
        try {
            linker.update("model", "view");
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
