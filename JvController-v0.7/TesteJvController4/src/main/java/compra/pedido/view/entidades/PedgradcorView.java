package compra.pedido.view.entidades;

import javax.swing.JButton;
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

    public JButton getButtonExcluir() {
        return buttonExcluir;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCor = new javax.swing.JLabel();
        buttonExcluir = new javax.swing.JButton();

        labelCor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCor.setText("Cor");

        buttonExcluir.setText("x");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(labelCor, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonExcluir)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(labelCor)
                .addComponent(buttonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonExcluir;
    private javax.swing.JLabel labelCor;
    // End of variables declaration//GEN-END:variables
}
