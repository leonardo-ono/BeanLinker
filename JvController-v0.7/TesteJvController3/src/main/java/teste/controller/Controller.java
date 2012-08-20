package teste.controller;

import ono.leo.jvcontroller.core.JvController;

/**
 *
 * @author leo
 */
public class Controller {

    private static JvController jc = JvController.getInstance();
    
    public static void main(String[] args) throws Exception {
        jc.registerModelClass("Model", "teste.model.Model");
        jc.registerModelClass("Cliente", "teste.model.Cliente");
        jc.registerModelClass("Endereco", "teste.model.Endereco");
        
        jc.registerViewClass("View", "teste.view.View");
        jc.registerViewClass("ClienteView", "teste.view.ClienteView");
        jc.registerViewClass("EnderecoView", "teste.view.EnderecoView");
        
        jc.registerControllerClass("Controller", "teste.controller.Controller");
        jc.registerConversorClass("IntegerConversor", "teste.conversor.IntegerConversor");
        
        jc.bindClassProperty("EnderecoView.textBairro.text", "Endereco.bairro", "", "");
        jc.bindClassProperty("EnderecoView.textCEP.text", "Endereco.cep", "", "");
        jc.bindClassProperty("EnderecoView.textCidade.text", "Endereco.cidade", "", "");
        jc.bindClassProperty("EnderecoView.textLogradouro.text", "Endereco.logradouro", "", "");
        jc.bindClassProperty("EnderecoView.textNumero.text", "Endereco.numero", "integerConversor", "");
        jc.bindClassProperty("EnderecoView.textUF.text", "Endereco.uf", "", "");
        jc.bindClassAction("EnderecoView.buttonExcluir.action", "model.excluir(Endereco)", "", "", "", "Excluir Endereco");

        jc.bindClassProperty("ClienteView.textNome.text", "Cliente.nome", "", "");
        // jc.bindClassCollection("ClienteView.enderecoViews", "Cliente.enderecos", "ClienteView.addEnderecoView", "ClienteView.removeEnderecoView");
        jc.bindClassCollection("", "Cliente.enderecos", "ClienteView.addEnderecoView", "ClienteView.removeEnderecoView", "EnderecoView");
        
        jc.bindClassBean("View.clienteView", "Model.cliente");
        
        jc.newInstance("model", "Model");
        jc.newInstance("view", "View");
        jc.newInstance("controller", "Controller");
        jc.newInstance("integerConversor", "IntegerConversor");
        
        jc.bindInstanceBean("view", "model");
        
        jc.getELContext().evaluate("view.setVisible(true)");
        
        jc.bindInstanceAction("view.buttonAdicionar.action", "model.adicionarEndereco()", "controller.ret", "", "", "Adicionar Endereco");
        jc.bindInstanceAction("view.buttonRemover.action", "model.removerEndereco()", "controller.ret", "", "", "Remover Endereco");
        jc.bindInstanceAction("view.buttonUpdateModel.action", "", "", "", "", "Update model");
        
        jc.updateView();
        
    }

}
