package compra.pedido.view.entidades;

import javax.swing.JButton;

public class PeddetView extends javax.swing.JPanel {

    public PeddetView() {
        initComponents();
    }

    public ProdutoView getProdutoView() {
        return produtoView;
    }

    public void addPedgradqtdeView(PedgradqtdeView qtdeView) {
        panel.add(qtdeView);
        panel.updateUI();
    }

    public void removePedgradqtdeView(PedgradqtdeView qtdeView) {
        panel.remove(qtdeView);
        panel.updateUI();
    }

    public JButton getButtonRemover() {
        return buttonRemover;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        produtoView = new compra.pedido.view.entidades.ProdutoView();
        buttonRemover = new javax.swing.JButton();
        panel = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Item do pedido"));

        produtoView.setBorder(javax.swing.BorderFactory.createTitledBorder("Produto"));

        buttonRemover.setText("Remover este item");

        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Grade"));
        panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.PAGE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                    .addComponent(produtoView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRemover))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(buttonRemover)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(produtoView, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonRemover;
    private javax.swing.JPanel panel;
    private compra.pedido.view.entidades.ProdutoView produtoView;
    // End of variables declaration//GEN-END:variables
}
