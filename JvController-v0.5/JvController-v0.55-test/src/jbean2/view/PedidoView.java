/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PedidoView.java
 *
 * Created on 12/08/2012, 09:03:58
 */
package jbean2.view;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import jbean2.annotation.BindCollection;

/**
 *
 * @author leo
 */
@jbean2.annotation.View("PedidoView")
public class PedidoView extends javax.swing.JPanel {

    List<ItemDoPedidoView> itensDoPedidoView = new ArrayList<ItemDoPedidoView>();

    /** Creates new form PedidoView */
    public PedidoView() {
        initComponents();
    }
    
    public List<ItemDoPedidoView> getItensDoPedidoView() {
        return itensDoPedidoView;
    }

    @BindCollection(
            property="PedidoView.itensDoPedidoView", 
            to="Pedido.itensDoPedido",
            viewAddMethod="PedidoView.panel.add",
            viewRemoveMethod="PedidoView.panel.remove",
            viewClass="ItemDoPedidoView")
    public JPanel getPanel() {
        return panel;
    }

    public void setItensDoPedidoView(List<ItemDoPedidoView> itensDoPedidoView) {
        
        System.out.println("itensDoPedidoView = " + itensDoPedidoView.size());
        
        this.itensDoPedidoView = itensDoPedidoView;
        List<Component> components = Arrays.asList(panel.getComponents());
        
        // Remove os itens inexistentes
        for (Component c : components) {
            ItemDoPedidoView item = (ItemDoPedidoView) c;
            if (!itensDoPedidoView.contains(item)) {
                panel.remove(item);
            }
        }
        
        // Adiciona novos itens
        for (ItemDoPedidoView item : itensDoPedidoView) {
            if (!components.contains(item)) {
                panel.add(item);
            }
        }
        
        // Atualiza interface
        panel.updateUI();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane1.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
