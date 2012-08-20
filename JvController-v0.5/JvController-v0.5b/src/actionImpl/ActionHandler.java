package actionImpl;

import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import jbean.BeanELAcess;
import jbean.annotation.Action;
import jbean.annotation.Controller;

/**
 *
 * @author leonardo
 */
public class ActionHandler {

    public static void createAction(Object sb) {
        Class c = sb.getClass();
        
        if (!c.isAnnotationPresent(Controller.class)) return;
        
        System.out.println("----------------------------------------------");
        for (Method method : c.getMethods()) {
            System.out.println("Method ... " + method);
            if (method.isAnnotationPresent(Action.class)) {
                Action action = (Action) method.getAnnotation(Action.class);
                System.out.println("Achou anotacao Action ... " + action);
                MeuAction meuAction = new MeuAction(action.label(), action.property(), action.update(), action.process(), method, sb);
                try {
                    BeanELAcess.getInstance().set(action.property(), meuAction);
                } catch (Exception ex) {
                    Logger.getLogger(ActionHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(-1);
                }
            }
        }
        System.out.println("----------------------------------------------");
    }
    
    private static class MeuAction extends AbstractAction {
        
        private Object bean = null;
        private String property = "";
        private String update = "";
        private String process = "";
        private Method method = null;

        public MeuAction(String label, String property, String update, String process, Method method, Object bean) {
            super(label);
            this.property = property;
            this.update = update;
            this.process = process;
            this.method = method;
            this.bean = bean;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // JController.getInstance().updateModel(update);
                method.invoke(bean);
                // JController.getInstance().updateView(process);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        
    }
    
}
