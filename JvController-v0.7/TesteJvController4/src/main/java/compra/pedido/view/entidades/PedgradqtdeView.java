package compra.pedido.view.entidades;

import javax.swing.JTextField;

public class PedgradqtdeView extends javax.swing.JPanel {

    private int tamCod;
    private int corCod;
    
    public PedgradqtdeView() {
        initComponents();
    }

    public PedgradqtdeView(int tamCod, int corCod) {
        this();
        this.tamCod = tamCod;
        this.corCod = corCod;
    }

    public int getCorCod() {
        return corCod;
    }

    public void setCorCod(int corCod) {
        this.corCod = corCod;
    }

    public int getTamCod() {
        return tamCod;
    }

    public void setTamCod(int tamCod) {
        this.tamCod = tamCod;
    }

    public JTextField getTextQuantidade() {
        return textQuantidade;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textQuantidade = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        textQuantidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        textQuantidade.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textQuantidade)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField textQuantidade;
    // End of variables declaration//GEN-END:variables
}
