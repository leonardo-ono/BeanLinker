package jbean2.core;

import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import jbean2.annotation.Action;
import jbean2.annotation.Model;
import jbean2.annotation.View;

/**
 *
 * @author leonardo
 */
public class ActionHandler {
    
    private static BeanELAcess be = BeanELAcess.getInstance();
    private static JController jc = JController.getInstance();
    
    public static void createAction(Object sb) {
        Class c = sb.getClass();
        
        if (!c.isAnnotationPresent(View.class)) return;
        
        System.out.println("----------------------------------------------");
        for (Method method : c.getMethods()) {
            System.out.println("Method ... " + method);
            if (method.isAnnotationPresent(Action.class)) {
                Action action = (Action) method.getAnnotation(Action.class);
                System.out.println("Achou anotacao Action ... " + action);
                MeuAction meuAction = new MeuAction(action.label(), action.property(), action.update(), action.process(), method, action.execute(), sb);
                try {
                    String viewId = action.property().replace(action.property().split("\\.")[0] + ".", "");
                    be.set(sb, viewId, meuAction);
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
        private String execute = "";
        private Method method = null;

        public MeuAction(String label, String property, String update, String process, Method method, String execute, Object bean) {
            super(label);
            this.property = property;
            this.update = update;
            this.process = process;
            this.method = method;
            this.bean = bean;
            this.execute = execute;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Object modelObj = null;
                for (String key : jc.getCollectionBindings().keySet()) {
                    Map<Object, Object> modelViewMap = jc.getCollectionBindings().get(key).getBinding();
                    for (Object key2 : modelViewMap.keySet()) {
                        System.out.println("key2=" + key2 + " / value=" + modelViewMap.get(key2));
                        if (bean.equals(modelViewMap.get(key2))) {
                            modelObj = key2;
                            break;
                        }
                    }
                }
                
                if (modelObj == null) {
                    for (String key : jc.getBeanBindings().keySet()) {
                        System.out.println("key=" + key + " / value=" + jc.getBeanBindings().get(key));
                        if (bean.equals(be.get(key))) {
                            modelObj = be.get(jc.getBeanBindings().get(key).getModel());
                            break;
                        }
                    }
                }
                be.addBean("invokeModelObj", modelObj);
                String invokeExecute = execute;
                Model model = (Model) modelObj.getClass().getAnnotation(Model.class);
                invokeExecute = invokeExecute.replace(model.value(), "invokeModelObj");
                
                jc.updateModel(process);
                
                be.evaluate(invokeExecute);

                jc.updateView(update);
            } catch (Exception ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
        }
        
    }
    
}
