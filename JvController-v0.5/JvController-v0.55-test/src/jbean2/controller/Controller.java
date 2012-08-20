package jbean2.controller;

import jbean2.core.BeanELAcess;
import jbean2.core.JController;

/**
 *
 * @author leonardo
 */
@jbean2.annotation.Controller("Controller")
public class Controller {
    
    private static JController jc = JController.getInstance();
    private static BeanELAcess be = BeanELAcess.getInstance();
    
    public static void main(String[] args) throws Exception {
        jc.newInstance("controller", "Controller");
        be.invoke("controller.init");
    }
    
    public void init() throws Exception {
        jc.newInstance("integerConversor", "IntegerConversor");
        jc.newInstance("bigDecimalConversor", "BigDecimalConversor");
        
        jc.newInstance("sistemaDePedido", "SistemaDePedido");
        jc.newInstance("view", "View");
        jc.bindBean("view", "sistemaDePedido");
        
        jc.updateView("view");
        be.set("view.visible", true);
    }
    
}
