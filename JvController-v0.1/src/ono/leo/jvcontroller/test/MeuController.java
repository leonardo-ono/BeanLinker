/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ono.leo.jvcontroller.test;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import ono.leo.jvcontroller.core.ConstraintException;
import ono.leo.jvcontroller.core.MainControllerImpl;

/**
 *
 * @author leo
 */
public class MeuController extends MainControllerImpl implements ActionListener {
    
    private MeuModel model;
    private Teste view;
    
    public void init() {
        view = new Teste();
        model = new MeuModel();
        
        addView("view", view);
        addBean("model", model);
        
        bindProperty("view.texto.text", "model.valor");
        bindProperty("view.list", "model.list");
        
        invoke("view.botao.addActionListener", this);
        invoke("view.texto.addActionListener", this);
        
        try {
            updateViews();
        } catch (ConstraintException ex) {
            Logger.getLogger(MeuController.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            updateBeans();
            invoke("model.adicionarNaLista");
            updateViews();
        } catch (ConstraintException ex) {
            JOptionPane.showMessageDialog(view, "Erro: " + ex.getMessage()
                    , ":: Atencao:", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        MeuController mc = new MeuController();
        mc.init();
    }
    
}
