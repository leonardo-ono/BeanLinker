package jbean2.model;

import java.util.ArrayList;
import java.util.List;
import jbean2.annotation.Model;

/**
 *
 * @author leonardo
 */
@Model("Pedido")
public class Pedido {

    private int numero;
    private List<ItemDoPedido> itensDoPedido = new ArrayList<ItemDoPedido>();

    public List<ItemDoPedido> getItensDoPedido() {
        return itensDoPedido;
    }

    public void setItensDoPedido(List<ItemDoPedido> itensDoPedido) {
        this.itensDoPedido = itensDoPedido;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Pedido{" + "numero=" + numero + ", itens=" + itensDoPedido + '}';
    }
    
}
