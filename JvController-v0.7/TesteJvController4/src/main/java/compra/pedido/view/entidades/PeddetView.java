package compra.pedido.view.entidades;

import java.awt.Component;
import javax.swing.JButton;

public class PeddetView extends javax.swing.JPanel {

    public PeddetView() {
        initComponents();
    }

    public GradeView getGradeView() {
        return gradeView;
    }

    public ProdutoView getProdutoView() {
        return produtoView;
    }

    public void addGradeViewItem(Component item) {
        gradeView.add(item);
        gradeView.updateUI();
    }

    public void removeGradeViewItem(Component item) {
        gradeView.remove(item);
        gradeView.updateUI();
    }

    public JButton getButtonRemover() {
        return buttonRemover;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        produtoView = new compra.pedido.view.entidades.ProdutoView();
        buttonRemover = new javax.swing.JButton();
        gradeView = new compra.pedido.view.entidades.GradeView();
        buttonAlterarProduto = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Item do pedido"));

        produtoView.setBorder(javax.swing.BorderFactory.createTitledBorder("Produto"));

        buttonRemover.setText("Remover este item");

        gradeView.setBorder(javax.swing.BorderFactory.createTitledBorder("Grade"));

        buttonAlterarProduto.setText("Alterar Produto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(produtoView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gradeView, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonRemover)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonAlterarProduto)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRemover)
                    .addComponent(buttonAlterarProduto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produtoView, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gradeView, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAlterarProduto;
    private javax.swing.JButton buttonRemover;
    private compra.pedido.view.entidades.GradeView gradeView;
    private compra.pedido.view.entidades.ProdutoView produtoView;
    // End of variables declaration//GEN-END:variables
}
