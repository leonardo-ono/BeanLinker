/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TelefoneView.java
 *
 * Created on 20/08/2012, 15:12:43
 */
package teste;

import javax.swing.JLabel;

/**
 *
 * @author leonardo
 */
public class TelefoneView extends javax.swing.JPanel {

    /** Creates new form TelefoneView */
    public TelefoneView() {
        initComponents();
    }

    public JLabel getLabelDDD() {
        return labelDDD;
    }

    public JLabel getLabelNome() {
        return labelNome;
    }

    public JLabel getLabelNumero() {
        return labelNumero;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNome = new javax.swing.JLabel();
        labelDDD = new javax.swing.JLabel();
        labelNumero = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        labelNome.setText("nome");

        labelDDD.setText("XX");

        labelNumero.setText("XXXXXXXX");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(labelNome, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDDD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelNumero)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelNome)
                .addComponent(labelDDD)
                .addComponent(labelNumero))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelDDD;
    private javax.swing.JLabel labelNome;
    private javax.swing.JLabel labelNumero;
    // End of variables declaration//GEN-END:variables
}
