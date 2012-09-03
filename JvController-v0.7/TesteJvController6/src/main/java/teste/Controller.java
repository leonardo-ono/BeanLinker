package teste;

import ono.leo.jvcontroller.core.JvController;

/**
 *
 * @author leonardo
 */
public class Controller {
    
    private static JvController jc 
            = JvController.getInstance();
    
    public static void main(String[] args) throws Exception {
    
        jc.registerModelClass("Model", "teste.Model");
        jc.registerModelClass("Telefone", "teste.Telefone");
        jc.registerViewClass("View", "teste.View");
        jc.registerViewClass("TelefoneView", "teste.TelefoneView");
        jc.registerControllerClass("Controller", "teste.Controller");

        jc.bindClassProperty("nome", "TelefoneView.labelNome.text", "Telefone.nome", "", "");
        jc.bindClassProperty("ddd", "TelefoneView.labelDDD.text", "Telefone.ddd", "", "");
        jc.bindClassProperty("numero", "TelefoneView.labelNumero.text", "Telefone.numero", "", "");
        
        jc.bindClassCollection("coll", "controller.concat('tel', modelItem.id)", "View.telefoneViews", "Model.telefones", "", "", "");
        
        jc.newInstance("model", "Model");
        jc.newInstance("view", "View");
        jc.newInstance("controller", "Controller");
        jc.getELContext().addBean("jc", jc);
        
        jc.bindInstanceBean("main", "view", "model");
        jc.bindInstanceProperty("", "view.textNome.text", "controller.nome", "", "");
        jc.bindInstanceProperty("", "view.textDDD.text", "controller.ddd", "", "");
        jc.bindInstanceProperty("", "view.textNumero.text", "controller.numero", "", "");
        jc.bindInstanceAction("view.buttonAdicionar.action", "model.addTelefone(controller.nome,controller.ddd,controller.numero)", "", "", "", "Adicionar Telefone");
        jc.bindInstanceAction("view.buttonRemover.action"
                , "model.removeTelefone( jc.getAssociatedModelInstance( view.list.getSelectedValue() ) )"
                , ""
                , "controller.concat('main.coll.tel', jc.getAssociatedModelInstance( view.list.getSelectedValue() ).id)"
                , "controller.concat('main.coll.tel', jc.getAssociatedModelInstance( view.list.getSelectedValue() ).id)"
                , "Remover Telefone");
        
        jc.getELContext().evaluate("view.setVisible(true)");
        jc.updateView();
    }
    
    public String concat(Object str1, Object str2) {
        return "" + str1 + str2;
    }
    
}
