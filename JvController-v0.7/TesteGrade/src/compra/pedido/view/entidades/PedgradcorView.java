package compra.pedido.view.entidades;

import javax.swing.JLabel;

public class PedgradcorView extends javax.swing.JPanel {

    private int linPos;
    private int corCod;

    public PedgradcorView() {
        initComponents();
    }
    
    public PedgradcorView(int linPos, int corCod, String label) {
        this();
        this.linPos = linPos;
        this.corCod = corCod;
        this.labelCor.setText(label);
    }

    public JLabel getLabelCor() {
        return labelCor;
    }

    public int getLinPos() {
        return linPos;
    }

    public void setLinPos(int linPos) {
        this.linPos = linPos;
    }

    public int getCorCod() {
        return corCod;
    }

    public void setCorCod(int corCod) {
        this.corCod = corCod;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCor = new javax.swing.JLabel();

        labelCor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCor.setText("Cor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(labelCor, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelCor)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelCor;
    // End of variables declaration//GEN-END:variables
}
