/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jbean2.model;

import jbean2.annotation.Model;

/**
 *
 * @author leonardo
 */
@Model("ItemDoPedido")
public class ItemDoPedido {
 
    private Produto produto = new Produto();
    private int quantidade = 999;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ItemDoPedido{" + "produto=" + produto + ", quantidade=" + quantidade + '}';
    }
    
    
}
