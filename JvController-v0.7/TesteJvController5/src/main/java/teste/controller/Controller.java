package teste.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import ono.leo.jvcontroller.core.JvController;

public class Controller {
    
    private static JvController jc = JvController.getInstance();
            
    public static void main( String[] args ) throws Exception {
        jc.registerModelClass("Model", "teste.model.Model");
        jc.registerModelClass("Cliente", "teste.model.Cliente");
        jc.registerModelClass("Telefone", "teste.model.Telefone");
        jc.registerViewClass("View", "teste.view.View");
        jc.registerViewClass("ClienteView", "teste.view.ClienteView");
        jc.registerViewClass("TelefoneView", "teste.view.TelefoneView");
        jc.registerControllerClass("Controller", "teste.controller.Controller");
        jc.registerConversorClass("IntegerConversor", "teste.conversor.IntegerConversor");
        // --- Vinculo entre classes ---
        jc.bindClassProperty("", "TelefoneView.textDDD.text", "Telefone.ddd", "", "");
        jc.bindClassProperty("", "TelefoneView.textNumero.text", "Telefone.numero", "", "");
        jc.bindClassAction("TelefoneView.buttonExcluir.action", "model.removerTelefone(Telefone)", "", "", "", "Remover");
        jc.bindClassProperty("", "ClienteView.textNome.text", "Cliente.nome", "", "");
        jc.bindClassProperty("", "ClienteView.textIdade.text", "Cliente.idade", "integerConversor", "");
        jc.bindClassCollection("", "", "", "Cliente.telefones", "ClienteView.addTelefone", "ClienteView.removeTelefone", "TelefoneView");
        jc.bindClassAction("ClienteView.buttonAdicionarTelefone.action", "model.adicionarTelefone()", "", "", "", "Adicionar Telefone");
        jc.bindClassBean("", "View.clienteView", "Model.cliente");
        jc.bindClassAction("View.buttonUpdateView.action", "controller.updateView()", "", "", "", "Update View");
        jc.bindClassAction("View.buttonUpdateModel.action", "controller.updateModel()", "", "", "", "Update Model");
        // --- Criar novas instancias ---
        jc.newInstance("controller", "Controller");
        jc.newInstance("model", "Model");
        jc.newInstance("view", "View");
        jc.newInstance("integerConversor", "IntegerConversor");
        // --- Vinculo entre instancias ---
        jc.bindInstanceBean("", "view", "model");
        // --- Inicializacao ---
        jc.getELContext().evaluate("view.setVisible(true)");        
        jc.updateView();
    }
    
    public void updateView() {
        try {
            System.out.println(jc.getELContext().evaluate("model.cliente"));
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateModel() {
        try {
            System.out.println(jc.getELContext().evaluate("model.cliente"));
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
