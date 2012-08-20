/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * View.java
 *
 * Created on 17/08/2012, 00:12:23
 */
package view;

import javax.swing.JButton;

/**
 *
 * @author leo
 */
public class View extends javax.swing.JFrame {

    /** Creates new form View */
    public View() {
        initComponents();
    }

    public ClienteView getClienteView() {
        return clienteView;
    }

    public JButton getButtonUpdateModel() {
        return buttonUpdateModel;
    }

    public void setButtonUpdateModel(JButton buttonUpdateModel) {
        this.buttonUpdateModel = buttonUpdateModel;
    }

    public JButton getButtonUpdateView() {
        return buttonUpdateView;
    }

    public void setButtonUpdateView(JButton buttonUpdateView) {
        this.buttonUpdateView = buttonUpdateView;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clienteView = new view.ClienteView();
        buttonUpdateView = new javax.swing.JButton();
        buttonUpdateModel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonUpdateView.setText("update view");

        buttonUpdateModel.setText("update model");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clienteView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonUpdateView)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
                        .addComponent(buttonUpdateModel)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(clienteView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonUpdateView)
                    .addComponent(buttonUpdateModel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new View().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonUpdateModel;
    private javax.swing.JButton buttonUpdateView;
    private view.ClienteView clienteView;
    // End of variables declaration//GEN-END:variables
}
