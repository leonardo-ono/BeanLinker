package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ono.leo.jvcontroller.core.JvController;

public class Main implements ActionListener {
    
    private static JvController jc
            = JvController.getInstance();
    
    public static void main( String[] args ) throws Exception {
        jc.registerModelClass("Ab", "model.Model");
        jc.registerModelClass("Bc", "model.Cliente");
        jc.registerModelClass("Cd", "model.Endereco");
        
        jc.registerViewClass("Vix", "view.View");
        jc.registerViewClass("Cli", "view.ClienteView");
        jc.registerViewClass("End", "view.EnderecoView");
        
        jc.registerControllerClass("Main", "controller.Main");
        
        jc.registerConversorClass("IntegerConversor", "conversor.IntegerConversor");
        jc.registerConversorClass("DataConversor", "conversor.DataConversor");
        
        jc.newInstance("integerConversor", "IntegerConversor");
        jc.newInstance("dataConversor", "DataConversor");
        jc.newInstance("ab", "Ab");
        jc.newInstance("vix", "Vix");
        jc.newInstance("main", "Main");
        
        // vincula endereco
        jc.bindClassProperty("", "End.textEnder.text", "Cd.endereco", "", "");
        jc.bindClassProperty("", "End.textNumero.text", "Cd.numero", "integerConversor", "");
        jc.bindClassProperty("", "End.textBairro.text", "Cd.bairro", "", "");
        jc.bindClassProperty("", "End.textCidade.text", "Cd.cidade", "", "");
        
        // vincula cliente
        jc.bindClassProperty("", "Cli.textCodigo.text", "Bc.codigo", "integerConversor", "");
        jc.bindClassProperty("", "Cli.textDataDeNascimento.text", "Bc.dataDeNascimento", "dataConversor", "");
        jc.bindClassProperty("", "Cli.textNome.text", "Bc.nome", "", "");
        jc.bindClassBean("", "Cli.enderecView", "Bc.ender");
        
        // vincula cliente da view com model
        jc.bindClassBean("", "Vix.clienteView", "Ab.cliente");
        
        // vincula instancias da view com model
        jc.bindInstanceBean("", "vix", "ab");
 
        jc.getELContext().set("vix.buttonUpdateModel.actionCommand", "updateModel");
        jc.getELContext().set("vix.buttonUpdateView.actionCommand", "updateView");
        jc.getELContext().evaluate("vix.buttonUpdateModel.addActionListener(main)");
        jc.getELContext().evaluate("vix.buttonUpdateView.addActionListener(main)");
        
        // atualiza view
        // jc.updateView();
        
        jc.getELContext().evaluate("vix.setVisible(true)");
    }

    public void actionPerformed(ActionEvent e) {
        Component c = null;
        try {
            c = (Component) jc.getELContext().get("vix");
            if (e.getActionCommand().equals("updateModel")) {
                jc.updateModel();
                jc.updateView();
                System.out.println(jc.getELContext().evaluate("ab"));
            }
            if (e.getActionCommand().equals("updateView")) {
                jc.updateView();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(c, ex.getMessage());
        }
    }

}
