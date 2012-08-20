package jbean2.model;

import java.math.BigDecimal;
import jbean2.annotation.Model;

/**
 *
 * @author leo
 */
@Model("SistemaDePedido")
public class SistemaDePedido {

    private Pedido pedido = new Pedido();
    
    public SistemaDePedido() {
    }

    public Pedido getPedido() { 
        return pedido;
    }
    
    public void abrirPedido(int numero) {
        pedido = new Pedido();
        pedido.setNumero(numero);
    }

    public void adicionarItemNovo() throws Exception {
        ItemDoPedido itemDoPedido = new ItemDoPedido();
        itemDoPedido.setQuantidade(66);
        itemDoPedido.getProduto().setCodigo((int) System.currentTimeMillis());
        itemDoPedido.getProduto().setDescricao("XXX " + System.currentTimeMillis());
        itemDoPedido.getProduto().getEndereco().setLogradouro("RUA MENDES JUNIOR");
        itemDoPedido.getProduto().getEndereco().setNumero(456);
        itemDoPedido.getProduto().setPreco(new BigDecimal("354.56"));
        pedido.getItensDoPedido().add(itemDoPedido);
    }

    public void removerItem(int codigoDoProduto) throws Exception {
        for (ItemDoPedido item : pedido.getItensDoPedido()) {
            if (item.getProduto().getCodigo() == codigoDoProduto) {
                pedido.getItensDoPedido().remove(item);
                break;
            }
        }
    }
    
}
