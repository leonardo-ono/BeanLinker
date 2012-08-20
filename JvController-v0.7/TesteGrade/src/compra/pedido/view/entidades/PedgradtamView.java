package compra.pedido.view.entidades;

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTamanho = new javax.swing.JLabel();

        labelTamanho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTamanho.setText("Tamanho");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTamanho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTamanho)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelTamanho;
    // End of variables declaration//GEN-END:variables
}
