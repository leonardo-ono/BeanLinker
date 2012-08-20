package compra.pedido.view.entidades;

import javax.swing.JButton;
import javax.swing.JLabel;

public class PedgradtamView extends javax.swing.JPanel {

    private int colPos;
    private int tamCod;
    
    public PedgradtamView() {
        initComponents();
    }

    public PedgradtamView(int colPos, int tamCod, String label) {
        this();
        this.colPos = colPos;
        this.tamCod = tamCod;
        this.labelTamanho.setText(label);
    }

    public JLabel getLabelTamanho() {
        return labelTamanho;
    }

    public int getColPos() {
        return colPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    public int getTamCod() {
        return tamCod;
    }

    public void setTamCod(int tamCod) {
        this.tamCod = tamCod;
    }

    public JButton getButtonExcluir() {
        return buttonExcluir;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTamanho = new javax.swing.JLabel();
        buttonExcluir = new javax.swing.JButton();

        labelTamanho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTamanho.setText("Tamanho");

        buttonExcluir.setText("x");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(labelTamanho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExcluir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelTamanho)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JLabel labelTamanho;
    // End of variables declaration//GEN-END:variables
}
